package com.vlabs.medicinetracker.singleMeasurement;

/**
 * Created by vlad on 3/4/16.
 */
public interface Presenter<Unit> {

    Unit createUnit(final String measurementString);

}
