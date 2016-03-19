package com.vlabs.medicinetracker.glucose;

import android.content.Context;
import android.os.Bundle;

import com.vlabs.medicinetracker.units.GlucoseLevel;
import com.vlabs.medicinetracker.units.domain.MeasurementItem;
import com.vlabs.medicinetracker.units.domain.UserActivity;
import com.vlabs.medicinetracker.units.measurements.GlucoseLevelMeasurement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.vlabs.medicinetracker.utils.DataUtils.currentDate;

/**
 * Created by vlad on 3/19/16.
 */
public class GlucoseLevelPresenter {

    private final GlucoseLevelView mView;
    private final UserActivitiesProvider mUserActivitiesProvider;
    private List<MeasurementItem<GlucoseLevelMeasurement>> mGeneratedItems = new ArrayList<>();

    private Date mMeasurementDate = currentDate();
    private int mHourOfDay;
    private int mMinutes;

    public GlucoseLevelPresenter(Context context, GlucoseLevelView view) {
        mView = view;
        mUserActivitiesProvider = new UserActivitiesProvider(context); // TODO: inject dependency

        mHourOfDay = mMeasurementDate.getHours();
        mMinutes = mMeasurementDate.getMinutes();
    }

    public void onCreate(final Bundle savedInstanceState) {
        mView.showUserActivities(mUserActivitiesProvider.getUserActivities());
        mView.updateMeasurementItemsList(mGeneratedItems);
        triggerMeasurementDateUpdate();
        triggerMeasurementTimeUpdate(mHourOfDay, mMinutes);
    }

    private void triggerMeasurementTimeUpdate(final int hourOfDay, final int minutes) {
        mView.updateMeasurementTime(hourOfDay, minutes);
    }

    public void onMeasureDateChanged(final Date time) {
        mMeasurementDate = time;
        triggerMeasurementDateUpdate();
    }

    public void onMeasureTimeChanged(final int hourOfDay, final int minutes) {
        mHourOfDay = hourOfDay;
        mMinutes = minutes;
        triggerMeasurementTimeUpdate(hourOfDay, minutes);
    }

    private void triggerMeasurementDateUpdate() {
        mView.updateMeasurementDate(mMeasurementDate);
    }

    public void onAddMeasurementValueClicked(final String glucoseLevelValue, final int spinnerIndex) {
        try {
            final GlucoseLevel glucoseLevel = new GlucoseLevel(glucoseLevelValue);
            final UserActivity userActivity = mUserActivitiesProvider.getUserActivities().get(spinnerIndex);
            final Date measurementDate = mMeasurementDate;
            measurementDate.setHours(mHourOfDay);
            measurementDate.setMinutes(mMinutes);

            final GlucoseLevelMeasurement measurement = new GlucoseLevelMeasurement(glucoseLevel, userActivity);
            final MeasurementItem<GlucoseLevelMeasurement> item = new MeasurementItem<>(measurement, measurementDate);
            mGeneratedItems.add(item);
            mView.onMeasurementItemsChanged();
        } catch (NumberFormatException ex) {
            mView.showNotification(ex.toString());
        }
    }
}
