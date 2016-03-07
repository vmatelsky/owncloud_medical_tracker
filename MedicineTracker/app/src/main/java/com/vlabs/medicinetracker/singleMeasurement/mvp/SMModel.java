package com.vlabs.medicinetracker.singleMeasurement.mvp;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vlad on 3/8/16.
 */
public class SMModel<Unit> {

    private final List<Pair<Unit, Date>> mAddedValues = new ArrayList<>();

    private Date mMeasurementDate = currentDate();

    public void addMeasurementValue(final Unit newValue, final Date withDate) {
        mAddedValues.add(Pair.create(newValue, withDate));
        // TODO: notify data change
    }

    public List<Pair<Unit, Date>> getValues() {
        return mAddedValues;
    }

    private Date currentDate() {
        return new Date();
    }

    public void setMeasurementDate(final Date measurementDate) {
        mMeasurementDate = measurementDate;
        // TODO: display updated data
    }

    public Date getMeasurementDate() {
        return mMeasurementDate;
    }
}
