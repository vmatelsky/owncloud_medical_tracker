package com.vlabs.medicinetracker.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by vlad on 3/24/16.
 */
@Database(name = TrackerDB.NAME, version = TrackerDB.VERSION)
public class TrackerDB {

    public static final String NAME = "trackerdb";

    public static final int VERSION = 3;
}
