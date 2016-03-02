package com.vlabs.medicinetracker;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.height_weight_button)
    void onHeightAndWeightClicked(final View view) {
        Snackbar.make(view, getString(R.string.height_and_weight) + " clicked", Snackbar.LENGTH_LONG).show();
    }

    @OnClick(R.id.medicine_reminder)
    void onMedicineReminderClicked(final View view) {
        Snackbar.make(view, getString(R.string.medicine_reminder) + " clicked", Snackbar.LENGTH_LONG).show();
    }

}
