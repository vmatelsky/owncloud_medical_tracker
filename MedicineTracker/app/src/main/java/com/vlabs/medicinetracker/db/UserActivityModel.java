package com.vlabs.medicinetracker.db;

import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

/**
 * Created by vlad on 3/28/16.
 */
public class UserActivityModel {

    public List<UserActivity> loadAll() {
        return new Select().from(UserActivity.class).queryList();
    }

    public void add(final String name) {
        final UserActivity table = new UserActivity(name);
        table.save();
    }

    public void remove(final UserActivity userActivity) {
        new Delete().from(UserActivity.class).where(UserActivity_Table.id.is(userActivity.id)).query();
    }

}
