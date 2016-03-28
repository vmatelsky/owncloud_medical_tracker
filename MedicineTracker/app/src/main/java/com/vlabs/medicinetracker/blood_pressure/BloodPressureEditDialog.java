package com.vlabs.medicinetracker.blood_pressure;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.vlabs.medicinetracker.R;
import com.vlabs.medicinetracker.db.BloodPressureMeasurement;
import com.vlabs.medicinetracker.units.domain.BloodPressure;
import com.vlabs.medicinetracker.units.domain.MeasurementItem;
import com.vlabs.medicinetracker.units.metric.mmHgArt;

import org.parceler.Parcels;

import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


import static com.vlabs.medicinetracker.utils.DataUtils.formattedDate;
import static com.vlabs.medicinetracker.utils.DataUtils.selectDate;

/**
 * Created by vlad on 3/28/16.
 */
public class BloodPressureEditDialog extends DialogFragment {

    private static final String EDIT_ITEM_KEY = "edit item key";

    public interface Listener {
        void onUpdateItem(final BloodPressureEditDialog bloodPressureEditDialog, final BloodPressureMeasurement originalItem, final MeasurementItem<BloodPressure> updates);
    }

    public static BloodPressureEditDialog createDialog(final BloodPressureMeasurement item) {
        final Bundle bundle = new Bundle();

        bundle.putParcelable(EDIT_ITEM_KEY, Parcels.wrap(item));

        final BloodPressureEditDialog dialog = new BloodPressureEditDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Bind(R.id.systolic)
    EditText mSystolic;

    @Bind(R.id.diastolic)
    EditText mDiastolic;

    @Bind(R.id.measurement_date)
    TextView mMeasureDate;

    private BloodPressureMeasurement mOriginalItem;
    private Date mWeightMeasureDate;

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

        mOriginalItem = Parcels.unwrap(getArguments().getParcelable(EDIT_ITEM_KEY));

        mWeightMeasureDate = mOriginalItem.measureDate;
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        updateMeasurementDateOnView();

        mSystolic.setText(mOriginalItem.systolic.toString());
        mDiastolic.setText(mOriginalItem.diastolic.toString());
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
            getListener().onUpdateItem(this, mOriginalItem, measurementItem);
        } catch (NumberFormatException ex) {
            Snackbar.make(view, "Transformation error", Snackbar.LENGTH_LONG).show();
        }
    }

    private void updateMeasurementDateOnView() {
        mMeasureDate.setText(formattedDate(mWeightMeasureDate));
    }

}
