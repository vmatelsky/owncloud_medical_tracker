package com.vlabs.medicinetracker.units;

/**
 * Created by vlad on 3/4/16.
 */
public class mmHgArt {

    private final int mValue;

    public mmHgArt(final String value) {
        this(Integer.valueOf(value));
    }

    public mmHgArt(final int value) {
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
