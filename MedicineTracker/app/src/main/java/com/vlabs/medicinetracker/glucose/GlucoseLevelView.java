package com.vlabs.medicinetracker.glucose;

import com.vlabs.medicinetracker.units.domain.GlucoseLevel;
import com.vlabs.medicinetracker.units.domain.MeasurementItem;
import com.vlabs.medicinetracker.units.domain.UserActivity;

import java.util.Date;
import java.util.List;

/**
 * Created by vlad on 3/19/16.
 */
public interface GlucoseLevelView {
    void showUserActivities(List<UserActivity> userActivities);

    void updateMeasurementDate(Date measurementTime);

    void updateMeasurementTime(int hourOfDay, int minutes);

    void showNotification(String notification);

    void updateMeasurementItemsList(List<MeasurementItem<GlucoseLevel>> generatedItems);

    void onMeasurementItemsChanged();

    void showSelectTimeDialog();

    void showSelectDateDialog();
}
