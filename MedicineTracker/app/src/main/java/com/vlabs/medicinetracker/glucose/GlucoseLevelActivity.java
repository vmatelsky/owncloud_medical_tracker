package com.vlabs.medicinetracker.glucose;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.vlabs.medicinetracker.MeasurementItemAdapter;
import com.vlabs.medicinetracker.R;
import com.vlabs.medicinetracker.units.domain.MeasurementItem;
import com.vlabs.medicinetracker.units.domain.UserActivity;
import com.vlabs.medicinetracker.units.measurements.GlucoseLevelMeasurement;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.vlabs.medicinetracker.utils.DataUtils.formattedDate;
import static com.vlabs.medicinetracker.utils.DataUtils.selectDate;
import static com.vlabs.medicinetracker.utils.DataUtils.selectTime;

/**
 * Created by vlad on 3/10/16.
 */
public class GlucoseLevelActivity extends AppCompatActivity implements GlucoseLevelView {

    @Bind(R.id.glucose_level)
    EditText mGlucoseLevel;

    @Bind(R.id.measurement_activity)
    Spinner mUserActivitiesList;

    @Bind(R.id.measurement_time)
    TextView mMeasurementTime;

    @Bind(R.id.measurement_date)
    TextView mMeasurementDate;

    @Bind(R.id.added_values)
    RecyclerView mAddedValues;

    GlucoseLevelPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glucose_level_layout);
        ButterKnife.bind(this);

        mPresenter = new GlucoseLevelPresenter(this, this);
        mPresenter.onCreate(savedInstanceState);

        mAddedValues.setLayoutManager(new LinearLayoutManager(this));;
        mAddedValues.setAdapter(new MeasurementItemAdapter<>(new ArrayList<MeasurementItem<GlucoseLevelMeasurement>>()));
    }

    @OnClick(R.id.measurement_date)
    void onMeasureTime(final View view) {
        selectTime(this, (timePicker, hourOfDay, minute) -> mPresenter.onMeasureTimeChanged(hourOfDay, minute));
    }

    @OnClick(R.id.measurement_date)
    void onMeasureDateClicked(final View view) {
        selectDate(this, (datePicker, year, monthOfYear, dayOfMonth) -> {
            mPresenter.onMeasureDateChanged(new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime());
        });
    }

    @OnClick(R.id.add)
    void onAddMeasurement(View view) {
        mPresenter.onAddMeasurementValueClicked(mGlucoseLevel.getText().toString().trim(), mUserActivitiesList.getSelectedItemPosition());
    }

    @Override
    public void showUserActivities(final List<UserActivity> userActivities) {
        mUserActivitiesList.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, userActivities));
    }

    @Override
    public void updateMeasurementDate(final Date measurementTime) {
        mMeasurementDate.setText(formattedDate(measurementTime));
    }

    @Override
    public void updateMeasurementTime(final int hourOfDay, final int minutes) {
        final String content = hourOfDay + " : " + minutes;
        mMeasurementTime.setText(content);
    }

    @Override
    public void showNotification(final String notification) {
        Snackbar.make(mAddedValues, notification, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onMeasurementItemsChanged() {
        mAddedValues.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void updateMeasurementItemsList(final List<MeasurementItem<GlucoseLevelMeasurement>> generatedItems) {
        mAddedValues.setAdapter(new MeasurementItemAdapter<>(generatedItems));
    }
}
