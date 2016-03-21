package com.vlabs.medicinetracker.glucose;

import android.content.Context;

import com.vlabs.medicinetracker.R;
import com.vlabs.medicinetracker.units.domain.UserActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad on 3/19/16.
 */
public class UserActivitiesProvider {

    private final List<UserActivity> mActivitiesList = new ArrayList<>();

    public UserActivitiesProvider(final Context context) {
        mActivitiesList.add(new UserActivity(context.getString(R.string.before_breakfast)));
        mActivitiesList.add(new UserActivity(context.getString(R.string.before_lunch)));
        mActivitiesList.add(new UserActivity(context.getString(R.string.hour_after_meal)));
        mActivitiesList.add(new UserActivity(context.getString(R.string.two_hours_after_meal)));
        mActivitiesList.add(new UserActivity(context.getString(R.string.after_sport)));
    }

    public List<UserActivity> getUserActivities() {
        return mActivitiesList;
    }


}
