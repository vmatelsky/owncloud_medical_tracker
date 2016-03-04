package com.vlabs.medicinetracker.singleMeasurement;

import com.vlabs.medicinetracker.units.Kilogram;

/**
 * Created by vlad on 3/4/16.
 */
public class WeightMeasurementPresenter implements Presenter<Kilogram> {

    @Override
    public Kilogram createUnit(final String measurementString) {
        return new Kilogram(Double.valueOf(measurementString));
    }

}
