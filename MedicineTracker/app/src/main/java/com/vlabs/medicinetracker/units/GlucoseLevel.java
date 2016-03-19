package com.vlabs.medicinetracker.units;

/**
 * Created by vlad on 3/4/16.
 */
public class GlucoseLevel {

    private final Double mValue;

    public GlucoseLevel(final String value) {
        this(Double.valueOf(value));
    }

    public GlucoseLevel(final Double value) {
        mValue = value;
    }

    public Double getValue() {
        return mValue;
    }

    @Override
    public String toString() {
        return String.valueOf(mValue);
    }

}
