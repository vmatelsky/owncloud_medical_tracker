package com.vlabs.medicinetracker.units.metric;

/**
 * Created by vlad on 3/4/16.
 */
public class Centimeter {

    private final int mValue;

    public Centimeter(final int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }

    @Override
    public String toString() {
        return String.valueOf(mValue);
    }

}
