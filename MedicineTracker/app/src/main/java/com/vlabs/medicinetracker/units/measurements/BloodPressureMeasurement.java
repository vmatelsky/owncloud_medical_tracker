package com.vlabs.medicinetracker.units.measurements;

import com.vlabs.medicinetracker.units.domain.BloodPressure;
import com.vlabs.medicinetracker.units.domain.MeasurementItem;

import java.util.Date;

/**
 * Created by vlad on 3/10/16.
 */
public class BloodPressureMeasurement extends MeasurementItem<BloodPressure> {

    public BloodPressureMeasurement(final BloodPressure bloodPressure, final Date date) {
        super(bloodPressure, date);
    }

}
