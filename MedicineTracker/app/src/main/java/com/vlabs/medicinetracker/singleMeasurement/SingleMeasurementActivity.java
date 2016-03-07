package com.vlabs.medicinetracker.singleMeasurement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vlabs.medicinetracker.singleMeasurement.mvp.SMInstance;
import com.vlabs.medicinetracker.singleMeasurement.mvp.SMPresenter;

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

        final SMPresenter presenter = presenterInstance.toPresenter(this);
        setContentView(presenter.getView());
    }

}
