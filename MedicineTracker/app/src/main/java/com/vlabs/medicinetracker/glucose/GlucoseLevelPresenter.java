package com.vlabs.medicinetracker.glucose;

import android.content.Context;
import android.os.Bundle;

import com.vlabs.medicinetracker.units.domain.GlucoseLevel;
import com.vlabs.medicinetracker.units.domain.MeasurementItem;
import com.vlabs.medicinetracker.units.domain.UserActivity;
import com.vlabs.medicinetracker.units.metric.mmol_L;

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
    private List<MeasurementItem<GlucoseLevel>> mGeneratedItems = new ArrayList<>();

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
            final mmol_L mmol_l = new mmol_L(glucoseLevelValue);
            final UserActivity userActivity = mUserActivitiesProvider.getUserActivities().get(spinnerIndex);
            final Date measurementDate = mMeasurementDate;
            measurementDate.setHours(mHourOfDay);
            measurementDate.setMinutes(mMinutes);

            final GlucoseLevel measurement = new GlucoseLevel(mmol_l, userActivity);
            final MeasurementItem<GlucoseLevel> item = new MeasurementItem<>(measurement, measurementDate);
            mGeneratedItems.add(item);
            mView.onMeasurementItemsChanged();
        } catch (NumberFormatException ex) {
            mView.showNotification(ex.toString());
        }
    }

    public void onMeasureTimeClicked() {
        mView.showSelectTimeDialog();
    }

    public void onSelectDateClicked() {
        mView.showSelectDateDialog();
    }
}
