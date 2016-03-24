package com.vlabs.medicinetracker;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by vlad on 3/24/16.
 */
public class MedicineTrackerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
