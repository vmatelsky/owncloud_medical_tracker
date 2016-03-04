package com.vlabs.medicinetracker;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Pair;
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

public class HeightActivity extends AppCompatActivity {

    public static final String DATE_PATTERN = "dd/MM/yyyy";
    @Bind(R.id.edit_height)
    EditText mEditHeight;

    @Bind(R.id.height_measure_date)
    TextView mHeightMeasureDate;

    @Bind(R.id.added_values)
    RecyclerView mAddedValues;

    private final List<Pair<String, String>> mAddedHeights = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);

        ButterKnife.bind(this);

        mHeightMeasureDate.setText(formatDate(currentDate()));

        mAddedValues.setLayoutManager(new LinearLayoutManager(this));
        mAddedValues.setAdapter(new AddedPairsAdapter(mAddedHeights));
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

    @OnClick(R.id.height_measure_date)
    void onHeightMeasureDateClicked(final View view) {
        selectDate((datePicker, year, monthOfYear, dayOfMonth) -> {
            final Date date = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
            mHeightMeasureDate.setText(formatDate(date));
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
