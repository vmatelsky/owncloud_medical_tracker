package com.vlabs.medicinetracker.units.domain;

import com.vlabs.medicinetracker.units.metric.Kilogram;

/**
 * Created by vlad on 3/8/16.
 */
public class Weight {

    private final Kilogram mInKilo;

    public Weight(final Kilogram inKilo) {
        mInKilo = inKilo;
    }

    public Kilogram getInKilo() {
        return mInKilo;
    }
}
