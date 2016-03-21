package com.vlabs.medicinetracker.units.domain;

/**
 * Created by vlad on 3/19/16.
 */
public class UserActivity {

    private final String mName;

    public UserActivity(final String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    @Override
    public String toString() {
        return mName;
    }
}
