package com.vlabs.medicinetracker.db;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.vlabs.medicinetracker.units.domain.BloodPressure;
import com.vlabs.medicinetracker.units.domain.MeasurementItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad on 3/24/16.
 */
public class BloodPressureMeasurementModel {

    public List<MeasurementItem<BloodPressure>> loadAll() {
        final List<BloodPressureTable> queryTable = new Select().from(BloodPressureTable.class).queryList();

        final List<MeasurementItem<BloodPressure>> result = new ArrayList<>();

        for (BloodPressureTable item : queryTable) {
            final BloodPressure bloodPressure = new BloodPressure(item.systolic, item.diastolic);
            result.add(new MeasurementItem<>(bloodPressure, item.measureDate));
        }
        return result;
    }

    public void save(final MeasurementItem<BloodPressure> measurementItem) {
        final BloodPressureTable table = new BloodPressureTable();
        table.systolic = measurementItem.getUnit().getSystolic();
        table.diastolic = measurementItem.getUnit().getDiastolic();
        table.measureDate = measurementItem.getDate();
        table.save();
    }
}
