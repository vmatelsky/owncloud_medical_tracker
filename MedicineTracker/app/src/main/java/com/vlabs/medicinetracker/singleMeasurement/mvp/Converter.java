package com.vlabs.medicinetracker.singleMeasurement.mvp;

/**
 * Created by vlad on 3/8/16.
 */
public interface Converter<From, To> {

    To convert(From from);

}
