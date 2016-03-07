package com.vlabs.medicinetracker.singleMeasurement.mvp;

import android.content.Context;
import android.view.View;

import java.util.Date;

/**
 * Created by vlad on 3/8/16.
 */
public interface ISMPresenter<Unit> {

    String measurementTitle(final Context context);

    void updateMeasurementValue(String newValue, Date withDate);

    void setMeasurementDate(Date measurementDate);

    View getView();
}
