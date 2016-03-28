package com.vlabs.medicinetracker.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by vlad on 3/19/16.
 */
@Table(database = TrackerDB.class)
public class UserActivity extends BaseModel {

    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String mName;

    // package protected for DBFlow
    UserActivity() {}

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
