package com.vlabs.medicinetracker.singleMeasurement;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vlabs.medicinetracker.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vlad on 3/4/16.
 */
public class SingleMeasurementView {

    private final View mRootView;

    @Bind(R.id.measurement_title)
    TextView mMeasurementTitle;

    @Bind(R.id.edit_measurement)
    EditText mMeasurementEditText;

    @Bind(R.id.measurement_date)
    EditText mMeasurementDate;

    @Bind(R.id.added_values)
    RecyclerView mAddedValues;

    public SingleMeasurementView(final Context context) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.single_measurement_view, null);
        ButterKnife.bind(this, mRootView);
    }

}
