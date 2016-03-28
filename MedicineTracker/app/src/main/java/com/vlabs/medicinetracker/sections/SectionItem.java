package com.vlabs.medicinetracker.sections;

import android.content.Intent;

import com.vlabs.medicinetracker.blood_pressure.show.BloodPressureActivity;
import com.vlabs.medicinetracker.glucose.GlucoseLevelActivity;
import com.vlabs.medicinetracker.MedicalReminderActivity;
import com.vlabs.medicinetracker.R;
import com.vlabs.medicinetracker.singleMeasurement.SingleMeasurementActivity;
import com.vlabs.medicinetracker.singleMeasurement.mvp.SMInstance;

/**
 * Created by vlad on 04/03/16.
 */
public enum SectionItem {

    HEIGHT(R.string.height, context -> SingleMeasurementActivity.createIntent(context, SMInstance.Height)),
    WEIGHT(R.string.weight, context -> SingleMeasurementActivity.createIntent(context, SMInstance.Weight)),
    PRESSURE(R.string.blood_pressure, context -> new Intent(context, BloodPressureActivity.class)),
    GLUCOSE_LEVEL(R.string.glucose_level, context -> new Intent(context, GlucoseLevelActivity.class)),
    MEDICAL_REMINDER(R.string.medicine_reminder, context -> new Intent(context, MedicalReminderActivity.class));

    private final int mTitleId;
    private final IntentFactory mIntentFactory;

    SectionItem(int title, final IntentFactory intentFactory) {
        mTitleId = title;
        mIntentFactory = intentFactory;
    }

    public int getTitleId() {
        return mTitleId;
    }

    public IntentFactory getIntentFactory() {
        return mIntentFactory;
    }
}
