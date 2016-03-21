package com.vlabs.medicinetracker.units.domain;

import com.vlabs.medicinetracker.units.metric.mmol_L;

/**
 * Created by vlad on 3/4/16.
 */
public class GlucoseLevel {

    private final mmol_L mGlucoseLevel;
    private final UserActivity mUserActivity;

    public GlucoseLevel(final mmol_L value, final UserActivity userActivity) {
        mGlucoseLevel = value;
        mUserActivity = userActivity;
    }

    public mmol_L getGlucoseLevel() {
        return mGlucoseLevel;
    }

    public UserActivity getUserActivity() {
        return mUserActivity;
    }

    @Override
    public String toString() {
        return mGlucoseLevel + " after " + mUserActivity;
    }

}
