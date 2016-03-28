package com.vlabs.medicinetracker.utils;

import android.os.Parcel;

import com.vlabs.medicinetracker.units.metric.mmHgArt;

import org.parceler.ParcelConverter;

/**
 * Created by vlad on 3/28/16.
 */
public class mmHgArtParcelConverter implements ParcelConverter<mmHgArt> {
    @Override
    public void toParcel(final mmHgArt input, final Parcel parcel) {
        parcel.writeString(input.toString());
    }

    @Override
    public mmHgArt fromParcel(final Parcel parcel) {
        return new mmHgArt(parcel.readString());
    }
}
