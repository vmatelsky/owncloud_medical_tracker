package com.vlabs.medicinetracker.units.domain;

import com.vlabs.medicinetracker.units.metric.Centimeter;

/**
 * Created by vlad on 3/8/16.
 */
public class Height {

    private final Centimeter mInCentimeters;

    public Height(final Centimeter inCentimeters) {
        mInCentimeters = inCentimeters;
    }

    public Centimeter getInCentimeters() {
        return mInCentimeters;
    }
}
