package com.vlabs.medicinetracker.glucose;

import android.content.Context;
import android.os.Bundle;

import com.vlabs.medicinetracker.db.UserActivityModel;
import com.vlabs.medicinetracker.units.domain.GlucoseLevel;
import com.vlabs.medicinetracker.units.domain.MeasurementItem;
import com.vlabs.medicinetracker.db.UserActivity;
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
    private final UserActivityModel mUserActivityModel;
    private List<MeasurementItem<GlucoseLevel>> mGeneratedItems = new ArrayList<>();

    private Date mMeasurementDate = currentDate();

    public GlucoseLevelPresenter(Context context, GlucoseLevelView view) {
        mView = view;
        mUserActivityModel = new UserActivityModel(); // TODO: inject dependency

    }

    public void onCreate(final Bundle savedInstanceState) {
        mView.showUserActivities(mUserActivityModel.loadAll());
        mView.updateMeasurementItemsList(mGeneratedItems);
        triggerMeasurementDateUpdate();
        triggerMeasurementTimeUpdate();
    }

    private void triggerMeasurementTimeUpdate() {
        mView.updateMeasurementTime(mMeasurementDate);
    }

    public void onMeasureDateChanged(final Date time) {
        mMeasurementDate = time;
        triggerMeasurementDateUpdate();
    }

    public void onMeasureTimeChanged(final int hourOfDay, final int minutes) {
        mMeasurementDate.setHours(hourOfDay);
        mMeasurementDate.setMinutes(minutes);
        triggerMeasurementTimeUpdate();
    }

    private void triggerMeasurementDateUpdate() {
        mView.updateMeasurementDate(mMeasurementDate);
    }

    public void onAddMeasurementValueClicked(final String glucoseLevelValue, final int spinnerIndex) {
        try {
            final mmol_L mmol_l = new mmol_L(glucoseLevelValue);
            final UserActivity userActivity = mUserActivityModel.loadAll().get(spinnerIndex);
            final GlucoseLevel measurement = new GlucoseLevel(mmol_l, userActivity);
            final MeasurementItem<GlucoseLevel> item = new MeasurementItem<>(measurement, mMeasurementDate);

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
