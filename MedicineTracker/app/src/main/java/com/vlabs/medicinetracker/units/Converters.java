package com.vlabs.medicinetracker.units;

import com.vlabs.medicinetracker.singleMeasurement.mvp.Converter;
import com.vlabs.medicinetracker.units.domain.Height;
import com.vlabs.medicinetracker.units.domain.Weight;
import com.vlabs.medicinetracker.units.metric.Centimeter;
import com.vlabs.medicinetracker.units.metric.Kilogram;

/**
 * Created by vlad on 3/8/16.
 */
public class Converters {

    public static Converter<String, Centimeter> STR_TO_CENTIMETERS = s -> new Centimeter(Integer.valueOf(s));
    public static Converter<String, Kilogram> STR_TO_KILOGRAM = s -> new Kilogram(Double.valueOf(s));

    public static Converter<String, mmHgArt> TO_mmHgArt = s -> new mmHgArt(Integer.valueOf(s));

    public static Converter<Centimeter, Height> TO_HEIGHT = Height::new;
    public static Converter<String, Height> STR_TO_HEIGHT = from -> TO_HEIGHT.convert(STR_TO_CENTIMETERS.convert(from));

    public static Converter<Kilogram, Weight> TO_WEIGHT = Weight::new;
    public static Converter<String, Weight> STR_TO_WEIGHT = from -> TO_WEIGHT.convert(STR_TO_KILOGRAM.convert(from));

}
