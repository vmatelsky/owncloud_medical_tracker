package com.vlabs.medicinetracker.units.metric;

/**
 * Created by vlad on 3/21/16.
 *
 * milimol per litre
 *
 * Formula for calculation of mg/dl from mmol/l: mg/dl = 18 × mmol/l
 * Formula for calculation of mmol/l from mg/dl: mmol/l = mg/dl / 18
 */
public class mmol_L {

    private final Double mValue;

    public mmol_L(final String value) {
        this(Double.valueOf(value));
    }

    public mmol_L(final Double value) {
        mValue = value;
    }

    public Double getValue() {
        return mValue;
    }

    @Override
    public String toString() {
        return String.valueOf(mValue);
    }

}
