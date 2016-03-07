package com.vlabs.medicinetracker.singleMeasurement.mvp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vlabs.medicinetracker.AddedPairsAdapter;
import com.vlabs.medicinetracker.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vlad on 3/8/16.
 */
public class SMView<Unit> {

    public static final String DATE_PATTERN = "dd/MM/yyyy";

    private final View mRootView;
    private final AddedPairsAdapter<Unit, Date> mAdapter;
    private final SMPresenter<Unit> mPresenter;
    private final SMModel<Unit> mModel;

    @Bind(R.id.measurement_title)
    TextView mMeasurementTitle;

    @Bind(R.id.edit_measurement)
    EditText mMeasurementEditText;

    @Bind(R.id.measurement_date)
    EditText mMeasurementDate;

    @Bind(R.id.added_values)
    RecyclerView mAddedValues;

    public SMView(final Context context,
                  final SMPresenter<Unit> presenter,
                  final SMModel<Unit> model) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.single_measurement_view, null);
        ButterKnife.bind(this, mRootView);

        mPresenter = presenter;
        mModel = model;

        mMeasurementTitle.setText(presenter.measurementTitle(context));
        displayDate(model.getMeasurementDate());

        mAddedValues.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new AddedPairsAdapter<>(model.getValues());
        mAddedValues.setAdapter(mAdapter);
    }

    public void notifyError(final String errorMessage) {
        Snackbar.make(mRootView, errorMessage, Snackbar.LENGTH_LONG).show();
    }

    public void notifyDataChanged() {
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.add_height)
    void onAddMeasurementItem(final View view) {
        final String measurementString = mMeasurementEditText.getText().toString().trim();
        mPresenter.updateMeasurementValue(measurementString, mModel.getMeasurementDate());
    }

    @OnClick(R.id.height_measure_date)
    void onMeasurementDateClicked(final View view) {
        selectDate((datePicker, year, monthOfYear, dayOfMonth) -> {
            final Date measurementDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
            mPresenter.setMeasurementDate(measurementDate);
        });
    }

    private void selectDate(final DatePickerDialog.OnDateSetListener listener) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(mRootView.getContext(), listener, year, month, day).show();
    }

    public void displayDate(final Date measurementDate) {
        mMeasurementDate.setText(formatDate(measurementDate));
    }

    private String formatDate(final Date date) {
        final SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN);
        return df.format(date);
    }

    public View rootView() {
        return mRootView;
    }
}
