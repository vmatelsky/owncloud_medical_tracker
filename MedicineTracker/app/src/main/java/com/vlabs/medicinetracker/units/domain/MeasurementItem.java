package com.vlabs.medicinetracker.units.domain;

import java.util.Date;

/**
 * Created by vlad on 3/10/16.
 */
public class MeasurementItem<Unit> {

    private final Unit mUnit;
    private final Date mDate;

    public MeasurementItem(Unit unit, Date date) {
        mUnit = unit;
        mDate = date;
    }

    public Unit getUnit() {
        return mUnit;
    }
    public Date getDate() {
        return mDate;
    }
}
