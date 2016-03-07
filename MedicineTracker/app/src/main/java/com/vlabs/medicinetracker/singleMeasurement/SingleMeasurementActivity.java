package com.vlabs.medicinetracker.singleMeasurement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vlabs.medicinetracker.singleMeasurement.mvp.ISMPresenter;
import com.vlabs.medicinetracker.singleMeasurement.mvp.SMInstance;
import com.vlabs.medicinetracker.singleMeasurement.mvp.SMPresenter;
import com.vlabs.medicinetracker.units.Converters;

/**
 * Created by vlad on 3/4/16.
 */
public class SingleMeasurementActivity extends AppCompatActivity {

    public static final String PRESENTER_TYPE_KEY = "SingleMeasurementActivity presenter type key";

    public static Intent createIntent(final Context context, SMInstance presenterType) {
        final Intent intent = new Intent(context, SingleMeasurementActivity.class);
        intent.putExtra(PRESENTER_TYPE_KEY, presenterType.toString());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final SMInstance presenterInstance = SMInstance.valueOf(getIntent().getStringExtra(PRESENTER_TYPE_KEY));

        final ISMPresenter presenter;
        switch (presenterInstance) {
            case Height:
                presenter = new SMPresenter<>(Converters.STR_TO_HEIGHT, this);
                break;
            case Weight:
                presenter = new SMPresenter<>(Converters.STR_TO_WEIGHT, this);
                break;
            default:
                throw new RuntimeException("unknown single measurement type");
        }

        setContentView(presenter.getView());
    }

}
