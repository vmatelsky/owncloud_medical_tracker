package com.vlabs.medicinetracker.singleMeasurement;

import com.vlabs.medicinetracker.units.Centimeter;

/**
 * Created by vlad on 3/4/16.
 */
public class HeightMeasurementPresenter implements Presenter<Centimeter> {

    @Override
    public Centimeter createUnit(final String measurementString) {
        return new Centimeter(Integer.valueOf(measurementString));
    }
}
