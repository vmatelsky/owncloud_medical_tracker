package com.vlabs.medicinetracker.singleMeasurement.mvp;

import com.jakewharton.rxrelay.BehaviorRelay;
import com.vlabs.medicinetracker.units.domain.MeasurementItem;

import java.util.Date;

import rx.Observable;

import static com.vlabs.medicinetracker.utils.DataUtils.currentDate;

/**
 * Created by vlad on 3/8/16.
 */
public class SMModel<Unit> {

    private Date mLastGeneratedDate = currentDate();
    private final BehaviorRelay<Date> mDateUpdated = BehaviorRelay.create();
    private final BehaviorRelay<MeasurementItem<Unit>> mAddedMeasurementsUpdated = BehaviorRelay.create();

    public SMModel() {
        mDateUpdated.call(mLastGeneratedDate);
    }

    public void addMeasurementValue(final Unit newValue, final Date withDate) {
        final MeasurementItem<Unit> item = new MeasurementItem<>(newValue, withDate);
        mAddedMeasurementsUpdated.call(item);
    }

    public Observable<MeasurementItem<Unit>> addedMeasurementItems() {
        return mAddedMeasurementsUpdated;
    }

    public void setMeasurementDate(final Date measurementDate) {
        mLastGeneratedDate = measurementDate;
        mDateUpdated.call(mLastGeneratedDate);
    }

    public Observable<Date> onDate() {
        return mDateUpdated;
    }

    public Date getLastGeneratedDate() {
        return mLastGeneratedDate;
    }
}
