package com.vlabs.medicinetracker.sections;

import com.vlabs.medicinetracker.HeightActivity;
import com.vlabs.medicinetracker.R;
import com.vlabs.medicinetracker.WeightActivity;

/**
 * Created by vlad on 04/03/16.
 */
public enum SectionItem {

    HEIGHT(R.string.height, HeightActivity.class),
    WEIGHT(R.string.weight, WeightActivity.class),
    PRESSURE(R.string.blood_pressure, HeightActivity.class),
    MEDICAL_REMINDER(R.string.medicine_reminder, HeightActivity.class);

    private final int mTitleId;
    private final Class mHandleActivityClass;

    SectionItem(int title, Class handleActivityClass) {
        mTitleId = title;
        mHandleActivityClass = handleActivityClass;
    }

    public int getTitleId() {
        return mTitleId;
    }

    public Class getHandleActivityClass() {
        return mHandleActivityClass;
    }
}
