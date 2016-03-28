package com.vlabs.medicinetracker.blood_pressure;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.vlabs.medicinetracker.R;
import com.vlabs.medicinetracker.units.domain.BloodPressure;
import com.vlabs.medicinetracker.units.domain.MeasurementItem;
import com.vlabs.medicinetracker.units.metric.mmHgArt;

import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.vlabs.medicinetracker.utils.DataUtils.currentDate;
import static com.vlabs.medicinetracker.utils.DataUtils.formattedDate;
import static com.vlabs.medicinetracker.utils.DataUtils.selectDate;

/**
 * Created by vlad on 3/25/16.
 */
public class BloodPressureAddDialog extends DialogFragment {

    public interface Listener {
        void onAddItem(final BloodPressureAddDialog bloodPressureAddDialog, final MeasurementItem<BloodPressure> measurementItem);
    }

    public static BloodPressureAddDialog createAddDialog() {
        return new BloodPressureAddDialog();
    }


    @Bind(R.id.systolic)
    EditText mSystolic;

    @Bind(R.id.diastolic)
    EditText mDiastolic;

    @Bind(R.id.measurement_date)
    TextView mMeasureDate;

    private Date mWeightMeasureDate = currentDate();

    private Listener getListener() {
        Activity activity = getActivity();
        if (activity instanceof Listener) {
            return (Listener) activity;
        } else {
            throw new RuntimeException(activity.toString() + "should implement " + Listener.class.getSimpleName());
        }
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_PopupOverlay);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        updateMeasurementDateOnView();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_blood_pressure, container, false);
    }

    @OnClick(R.id.measurement_date)
    void onMeasureDateClicked(final View view) {
        selectDate(getActivity(), (datePicker, year, monthOfYear, dayOfMonth) -> {
            mWeightMeasureDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
            updateMeasurementDateOnView();
        });
    }

    @OnClick(R.id.close)
    void onCloseClicked(final View view) {
        dismiss();
    }

    @OnClick(R.id.confirm)
    void onAddBloodPressureMeasurementClicked(View view) {
        try {
            final mmHgArt systolic = new mmHgArt(mSystolic.getText().toString().trim());
            final mmHgArt diastolic = new mmHgArt(mDiastolic.getText().toString().trim());

            final BloodPressure bloodPressure = new BloodPressure(systolic, diastolic);
            final MeasurementItem<BloodPressure> measurementItem = new MeasurementItem<>(bloodPressure, mWeightMeasureDate);
            getListener().onAddItem(this, measurementItem);
        } catch (NumberFormatException ex) {
            Snackbar.make(view, "Transformation error", Snackbar.LENGTH_LONG).show();
        }
    }

    private void updateMeasurementDateOnView() {
        mMeasureDate.setText(formattedDate(mWeightMeasureDate));
    }

}
