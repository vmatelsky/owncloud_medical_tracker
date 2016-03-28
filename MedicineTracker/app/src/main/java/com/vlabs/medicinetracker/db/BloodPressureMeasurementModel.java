package com.vlabs.medicinetracker.db;

import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.vlabs.medicinetracker.units.domain.BloodPressure;
import com.vlabs.medicinetracker.units.domain.MeasurementItem;

import java.util.List;

/**
 * Created by vlad on 3/24/16.
 */
public class BloodPressureMeasurementModel {

    public List<BloodPressureMeasurement> loadAll() {
        return new Select().from(BloodPressureMeasurement.class).queryList();
    }

    public void save(final MeasurementItem<BloodPressure> measurementItem) {
        final BloodPressureMeasurement table = new BloodPressureMeasurement();
        table.systolic = measurementItem.getUnit().getSystolic();
        table.diastolic = measurementItem.getUnit().getDiastolic();
        table.measureDate = measurementItem.getDate();
        table.save();
    }

    public void remove(final BloodPressureMeasurement measurement) {
        new Delete().from(BloodPressureMeasurement.class).where(BloodPressureMeasurement_Table.id.is(measurement.id)).query();
    }
}
