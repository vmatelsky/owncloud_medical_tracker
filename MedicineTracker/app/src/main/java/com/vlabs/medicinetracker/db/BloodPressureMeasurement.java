package com.vlabs.medicinetracker.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.converter.DateConverter;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.vlabs.medicinetracker.units.metric.mmHgArt;

import java.util.Date;

/**
 * Created by vlad on 3/24/16.
 */
@Table(database = TrackerDB.class)
public class BloodPressureMeasurement extends BaseModel {

    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    public mmHgArt systolic;

    @Column
    public mmHgArt diastolic;

    @Column(typeConverter = DateConverter.class)
    public Date measureDate;

}
