package com.vlabs.medicinetracker.units;

/**
 * Created by vlad on 3/4/16.
 */
public class Kilogram {

    private final Double mValue;

    public Kilogram(final Double value) {
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
