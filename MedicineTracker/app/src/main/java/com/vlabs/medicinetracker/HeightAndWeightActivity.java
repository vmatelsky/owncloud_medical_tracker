package com.vlabs.medicinetracker;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HeightAndWeightActivity extends AppCompatActivity {

    public static final String DATE_PATTERN = "dd/MM/yyyy";
    @Bind(R.id.edit_height)
    EditText mEditHeight;

    @Bind(R.id.edit_weight)
    EditText mEditWeight;

    @Bind(R.id.weight_measure_date)
    TextView mWeightMeasureDate;

    @Bind(R.id.height_measure_date)
    TextView mHeightMeasureDate;

    @Bind(R.id.added_values)
    RecyclerView mAddedValues;

    private final List<Pair<String, String>> mAddedWeights = new ArrayList<>();
    private final List<Pair<String, String>> mAddedHeights = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height_and_weight);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                                       Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                               .setAction("Action", null).show());

        ButterKnife.bind(this);

        mWeightMeasureDate.setText(formatDate(currentDate()));
        mHeightMeasureDate.setText(formatDate(currentDate()));

        mAddedValues.setLayoutManager(new LinearLayoutManager(this));
        mAddedValues.setAdapter(new AddedPairsAdapter(new ArrayList<>()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.height_and_weight_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_heights:
                mAddedValues.setAdapter(new AddedPairsAdapter(mAddedHeights));
                return true;
            case R.id.menu_weights:
                mAddedValues.setAdapter(new AddedPairsAdapter(mAddedWeights));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.add_height)
    void onAddHeight(final View view) {

        final String height = mEditHeight.getText().toString().trim();
        if (TextUtils.isEmpty(height)) {
            Snackbar.make(view, "please specify height", Snackbar.LENGTH_LONG).show();
        } else {
            final Pair<String, String> pair = Pair.create(height, mHeightMeasureDate.getText().toString().trim());
            mAddedHeights.add(pair);
            mAddedValues.getAdapter().notifyDataSetChanged();
        }
    }

    @OnClick(R.id.add_weight)
    void onAddWeight(final View view) {

        final String weight = mEditWeight.getText().toString().trim();
        if (TextUtils.isEmpty(weight)) {
            Snackbar.make(view, "please specify weight", Snackbar.LENGTH_LONG).show();
        } else {
            final Pair<String, String> pair = Pair.create(weight, mWeightMeasureDate.getText().toString().trim());
            mAddedWeights.add(pair);
            mAddedValues.getAdapter().notifyDataSetChanged();
        }
    }


    @OnClick(R.id.weight_measure_date)
    void onWeightMeasureDateClicked(final View view) {
        selectDate((datePicker, year, monthOfYear, dayOfMonth) -> {
            final Date date = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
            mWeightMeasureDate.setText(formatDate(date));
        });
    }

    @OnClick(R.id.height_measure_date)
    void onHeightMeasureDateClicked(final View view) {
        selectDate((datePicker, year, monthOfYear, dayOfMonth) -> {
            final Date date = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
            mWeightMeasureDate.setText(formatDate(date));
        });
    }

    private void selectDate(final OnDateSetListener listener) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, listener, year, month, day).show();
    }

    private Date currentDate() {
        return new Date();
    }

    private String formatDate(final Date date) {
        final SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN);
        return df.format(date);
    }

}
