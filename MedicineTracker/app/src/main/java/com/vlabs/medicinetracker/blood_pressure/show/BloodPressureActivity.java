package com.vlabs.medicinetracker.blood_pressure.show;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.vlabs.medicinetracker.R;
import com.vlabs.medicinetracker.blood_pressure.BloodPressureAddDialog;
import com.vlabs.medicinetracker.blood_pressure.BloodPressureAddDialog.Listener;
import com.vlabs.medicinetracker.blood_pressure.ItemTouchHelperCallback;
import com.vlabs.medicinetracker.db.BloodPressureMeasurement;
import com.vlabs.medicinetracker.units.domain.BloodPressure;
import com.vlabs.medicinetracker.units.domain.MeasurementItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vlad on 3/10/16.
 */
public class BloodPressureActivity extends AppCompatActivity
        implements Listener, BloodPressureView {

    @Bind(R.id.added_values)
    RecyclerView mAddedValues;

    private BloodPressurePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blood_pressure_layout);

        ButterKnife.bind(this);

        mAddedValues.setLayoutManager(new LinearLayoutManager(this));

        mPresenter = new BloodPressurePresenter(this);
        mPresenter.onCreate(savedInstanceState);
    }

    @OnClick(R.id.fab)
    void onAddClicked(final View view) {
        mPresenter.handleOnAddClicked();
    }

    @Override
    public void onConfirmSubmitted(final BloodPressureAddDialog bloodPressureAddDialog, final MeasurementItem<BloodPressure> measurementItem) {
        mPresenter.onAddMeasurementClicked(measurementItem);
        bloodPressureAddDialog.dismiss();
    }

    @Override
    public void loadMeasurements(final BloodPressureAdapter adapter) {
        mAddedValues.setAdapter(adapter);

        final ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        final ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mAddedValues);
    }

    @Override
    public void showAddItemDialog() {
        BloodPressureAddDialog.createAddDialog().show(getFragmentManager(), null);
    }

    @Override
    public void showEditDialogForItem(final BloodPressureMeasurement item) {

    }

    @Override
    public boolean onLongClick(final int position) {
        return mPresenter.onLongClick(position);
    }

    @Override
    public void onClick(final int position) {
        mPresenter.onClick(position);
    }

    @Override
    public void showNotification(final String message) {
        Snackbar.make(mAddedValues, message, Snackbar.LENGTH_LONG).show();
    }
}
