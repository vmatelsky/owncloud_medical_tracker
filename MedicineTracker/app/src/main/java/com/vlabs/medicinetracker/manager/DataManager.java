package com.vlabs.medicinetracker.manager;

import com.vlabs.medicinetracker.units.domain.MeasurementItem;
import com.vlabs.medicinetracker.units.measurements.HeightMeasurement;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by vlad on 3/10/16.
 */
public class DataManager {

    private final List<HeightMeasurement> mAddedValues = new ArrayList<>();

    public Observable<List<HeightMeasurement>> heightMeasurements() {
        final ArrayList<List<HeightMeasurement>> arrayList = new ArrayList<>();
        arrayList.add(mAddedValues);
        return Observable.from(arrayList);
    }

    public <Unit> void addHeightMeasurement(final MeasurementItem<Unit> item) {

    }
}
