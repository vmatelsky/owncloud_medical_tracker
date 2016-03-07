package com.vlabs.medicinetracker.singleMeasurement.mvp;

import android.content.Context;

import com.vlabs.medicinetracker.units.Converters;

/**
 * Created by vlad on 3/4/16.
 */
public enum SMInstance {
    Weight {
        @Override
        public SMPresenter toPresenter(final Context context) {
            return new SMPresenter<>(Converters.STR_TO_WEIGHT, context);
        }
    }, Height {
        @Override
        public SMPresenter toPresenter(final Context context) {
            return new SMPresenter<>(Converters.STR_TO_HEIGHT, context);
        }
    };


    public abstract SMPresenter toPresenter(final Context context);
}
