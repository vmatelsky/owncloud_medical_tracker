package com.vlabs.medicinetracker.blood_pressure.show;

import com.vlabs.medicinetracker.TwoItemsViewHolder.Listener;
import com.vlabs.medicinetracker.db.BloodPressureMeasurement;

/**
 * Created by vlad on 3/28/16.
 */
public interface BloodPressureView extends Listener {

    void loadMeasurements(BloodPressureAdapter adapter);

    void showAddItemDialog();

    @Override
    boolean onLongClick(final int position);

    @Override
    void onClick(final int position);

    void showEditDialogForItem(BloodPressureMeasurement item);

    void showNotification(String message);
}
