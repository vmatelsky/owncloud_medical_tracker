package com.vlabs.medicinetracker.units.domain;

import com.vlabs.medicinetracker.units.mmHgArt;

/**
 * Created by vlad on 3/10/16.
 */
public class BloodPressure {

    private final mmHgArt mSystolic;
    private final mmHgArt mDiastolic;

    public BloodPressure(mmHgArt systolic, mmHgArt diastolic) {
        mSystolic = systolic;
        mDiastolic = diastolic;
    }

    public mmHgArt getSystolic() {
        return mSystolic;
    }

    public mmHgArt getDiastolic() {
        return mDiastolic;
    }

    @Override
    public String toString() {
        return String.format("%s/%s", mSystolic, mDiastolic);
    }
}
