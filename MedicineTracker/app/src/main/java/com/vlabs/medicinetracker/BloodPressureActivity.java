package com.vlabs.medicinetracker;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vlabs.medicinetracker.units.domain.BloodPressure;
import com.vlabs.medicinetracker.units.domain.MeasurementItem;
import com.vlabs.medicinetracker.units.metric.mmHgArt;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.vlabs.medicinetracker.utils.DataUtils.currentDate;
import static com.vlabs.medicinetracker.utils.DataUtils.formattedDate;
import static com.vlabs.medicinetracker.utils.DataUtils.selectDate;

/**
 * Created by vlad on 3/10/16.
 */
public class BloodPressureActivity extends AppCompatActivity {

    @Bind(R.id.systolic)
    EditText mSystolic;

    @Bind(R.id.diastolic)
    EditText mDiastolic;

    @Bind(R.id.measurement_date)
    TextView mMeasureDate;

    @Bind(R.id.added_values)
    RecyclerView mAddedValues;

    private Date mWeightMeasureDate = currentDate();
    private final List<MeasurementItem<BloodPressure>> mGeneratedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blood_pressure_layout);

        ButterKnife.bind(this);

        updateMeasurementDateOnView();

        mAddedValues.setLayoutManager(new LinearLayoutManager(this));
        mAddedValues.setAdapter(new MeasurementItemAdapter<>(mGeneratedItems));
    }

    @OnClick(R.id.measurement_date)
    void onMeasureDateClicked(final View view) {
        selectDate(this, (datePicker, year, monthOfYear, dayOfMonth) -> {
            mWeightMeasureDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
            updateMeasurementDateOnView();
        });
    }

    @OnClick(R.id.add_blood_pressure_measurement)
    void onAddBloodPressureMeasurementClicked(View view) {
        try {
            final mmHgArt systolic = new mmHgArt(mSystolic.getText().toString().trim());
            final mmHgArt diastolic = new mmHgArt(mDiastolic.getText().toString().trim());

            final BloodPressure bloodPressure = new BloodPressure(systolic, diastolic);
            final MeasurementItem<BloodPressure> measurementItem = new MeasurementItem<>(bloodPressure, mWeightMeasureDate);
            mGeneratedItems.add(measurementItem);
            mAddedValues.getAdapter().notifyDataSetChanged();
        } catch (NumberFormatException ex) {
            Snackbar.make(view, "Transformation error", Snackbar.LENGTH_LONG).show();
        }
    }

    private void updateMeasurementDateOnView() {
        mMeasureDate.setText(formattedDate(mWeightMeasureDate));
    }
}
