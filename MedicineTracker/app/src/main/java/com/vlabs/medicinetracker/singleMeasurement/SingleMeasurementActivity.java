package com.vlabs.medicinetracker.singleMeasurement;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
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

import com.vlabs.medicinetracker.AddedPairsAdapter;
import com.vlabs.medicinetracker.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.vlabs.medicinetracker.singleMeasurement.SingleInstancePresenterFactory.createPresenter;

/**
 * Created by vlad on 3/4/16.
 */
public class SingleMeasurementActivity<Unit> extends AppCompatActivity {

    public static final String PRESENTER_TYPE_KEY = "SingleMeasurementActivity presenter type key";
    public static final String DATE_PATTERN = "dd/MM/yyyy";

    public static Intent createIntent(final Context context, SingleMeasurementPresenterInstance presenterType) {
        final Intent intent = new Intent(context, SingleMeasurementActivity.class);
        intent.putExtra(PRESENTER_TYPE_KEY, presenterType.toString());
        return intent;
    }

    @Bind(R.id.measurement_title)
    TextView mMeasurementTitle;

    @Bind(R.id.edit_measurement)
    EditText mMeasurementEditText;

    @Bind(R.id.measurement_date)
    EditText mMeasurementDateEditText;

    Date mMeasureDate = currentDate();

    @Bind(R.id.added_values)
    RecyclerView mAddedValues;

    private final List<Pair<Unit, Date>> mAddedHeights = new ArrayList<>();

    Presenter<Unit> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final SingleMeasurementPresenterInstance presenterInstance = SingleMeasurementPresenterInstance.valueOf(getIntent().getStringExtra(PRESENTER_TYPE_KEY));
        mPresenter = createPresenter(presenterInstance);

        setContentView(R.layout.single_measurement_view);

        ButterKnife.bind(this);

        mMeasurementDateEditText.setText(formatDate(mMeasureDate));

        mAddedValues.setLayoutManager(new LinearLayoutManager(this));
        mAddedValues.setAdapter(new AddedPairsAdapter<>(mAddedHeights));
    }

    @OnClick(R.id.add_height)
    void onAddMeasurementItem(final View view) {

        final String measurementString = mMeasurementEditText.getText().toString().trim();
        if (TextUtils.isEmpty(measurementString)) {
            Snackbar.make(view, "please specify value", Snackbar.LENGTH_LONG).show();
        } else {
            final Unit unit = mPresenter.createUnit(measurementString);
            mAddedHeights.add(Pair.create(unit, mMeasureDate));
            mAddedValues.getAdapter().notifyDataSetChanged();
        }
    }

    @OnClick(R.id.height_measure_date)
    void onMeasurementDateClicked(final View view) {
        selectDate((datePicker, year, monthOfYear, dayOfMonth) -> {
            mMeasureDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
            mMeasurementEditText.setText(formatDate(mMeasureDate));
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
