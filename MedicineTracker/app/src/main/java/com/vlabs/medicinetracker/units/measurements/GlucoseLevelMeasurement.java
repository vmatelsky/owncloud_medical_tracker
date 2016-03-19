package com.vlabs.medicinetracker.units.measurements;

import com.vlabs.medicinetracker.units.domain.UserActivity;
import com.vlabs.medicinetracker.units.GlucoseLevel;

/**
 * Created by vlad on 3/19/16.
 */
public class GlucoseLevelMeasurement {

    private final GlucoseLevel mGlucoseLevel;
    private final UserActivity mUserActivity;

    public GlucoseLevelMeasurement(final GlucoseLevel value, final UserActivity userActivity) {
        mGlucoseLevel = value;
        mUserActivity = userActivity;
    }

    public GlucoseLevel getGlucoseLevel() {
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
