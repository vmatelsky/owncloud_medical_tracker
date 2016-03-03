package com.vlabs.medicinetracker;

/**
 * Created by vlad on 04/03/16.
 */
public enum SectionItem {

    HEIGHT_AND_WEIGHT(R.string.height_and_weight, HeightAndWeightActivity.class),
    PRESSURE(R.string.blood_pressure, HeightAndWeightActivity.class),
    MEDICAL_REMINDER(R.string.medicine_reminder, HeightAndWeightActivity.class);

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
