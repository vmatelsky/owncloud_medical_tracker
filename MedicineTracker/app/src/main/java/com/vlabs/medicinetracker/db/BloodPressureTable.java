package com.vlabs.medicinetracker.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.converter.DateConverter;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.vlabs.medicinetracker.units.metric.mmHgArt;
import com.vlabs.medicinetracker.units.mmHgArtConverter;

import java.util.Date;

/**
 * Created by vlad on 3/24/16.
 */
@Table(database = TrackerDB.class)
public class BloodPressureTable extends BaseModel {

    @PrimaryKey(autoincrement = true)
    long id;

    @Column(typeConverter = mmHgArtConverter.class)
    mmHgArt systolic;

    @Column(typeConverter = mmHgArtConverter.class)
    mmHgArt diastolic;

    @Column(typeConverter = DateConverter.class)
    Date measureDate;

}
