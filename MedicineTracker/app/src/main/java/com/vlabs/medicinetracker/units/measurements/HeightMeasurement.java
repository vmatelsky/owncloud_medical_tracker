package com.vlabs.medicinetracker.units.measurements;

import com.vlabs.medicinetracker.units.domain.Height;
import com.vlabs.medicinetracker.units.domain.MeasurementItem;

import java.util.Date;

/**
 * Created by vlad on 3/10/16.
 */
public class HeightMeasurement extends MeasurementItem<Height> {

    public HeightMeasurement(final Height height, final Date date) {
        super(height, date);
    }
}
