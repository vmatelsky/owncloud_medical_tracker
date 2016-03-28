package com.vlabs.medicinetracker.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.converter.DateConverter;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.vlabs.medicinetracker.units.metric.mmHgArt;
import com.vlabs.medicinetracker.utils.mmHgArtParcelConverter;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import java.util.Date;

/**
 * Created by vlad on 3/24/16.
 */
@Parcel
@Table(database = TrackerDB.class)
public class BloodPressureMeasurement extends BaseModel {

    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    @ParcelPropertyConverter(mmHgArtParcelConverter.class)
    public mmHgArt systolic;

    @Column
    @ParcelPropertyConverter(mmHgArtParcelConverter.class)
    public mmHgArt diastolic;

    @Column(typeConverter = DateConverter.class)
    public Date measureDate;

    @Override
    public boolean equals(Object other){
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof BloodPressureMeasurement))return false;
        BloodPressureMeasurement otherMyClass = (BloodPressureMeasurement)other;
        return id == otherMyClass.id;
    }

}
