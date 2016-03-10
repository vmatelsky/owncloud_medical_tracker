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

import com.vlabs.medicinetracker.MeasurementItemAdapter;
import com.vlabs.medicinetracker.R;
import com.vlabs.medicinetracker.units.domain.MeasurementItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.vlabs.medicinetracker.utils.DataUtils.formattedDate;

/**
 * Created by vlad on 3/8/16.
 */
public class SMView<Unit> {

    private final View mRootView;
    private final SMPresenter<Unit> mPresenter;
    private final SMModel<Unit> mModel;

    @Bind(R.id.measurement_title)
    TextView mMeasurementTitle;

    @Bind(R.id.edit_measurement)
    EditText mMeasurementEditText;

    @Bind(R.id.measurement_date)
    TextView mMeasurementDate;

    @Bind(R.id.added_values)
    RecyclerView mAddedValues;

    private final List<MeasurementItem<Unit>> mGeneratedItems = new ArrayList<>();

    public SMView(final Context context,
                  final SMPresenter<Unit> presenter,
                  final SMModel<Unit> model) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.single_measurement_view, null);
        ButterKnife.bind(this, mRootView);

        mPresenter = presenter;
        mModel = model;

        mMeasurementTitle.setText(presenter.measurementTitle());
        mMeasurementEditText.setHint(presenter.measurementTitle());

        model.onDate().subscribe(this::displayDate);
        model.addedMeasurementItems().subscribe(this::handleNewMeasurementItem);

        mAddedValues.setLayoutManager(new LinearLayoutManager(context));
        mAddedValues.setAdapter(new MeasurementItemAdapter<>(mGeneratedItems));
    }

    private void handleNewMeasurementItem(final MeasurementItem<Unit> unitMeasurementItem) {
        mGeneratedItems.add(unitMeasurementItem);
        mAddedValues.getAdapter().notifyDataSetChanged();
    }

    public void notifyError(final String errorMessage) {
        Snackbar.make(mRootView, errorMessage, Snackbar.LENGTH_LONG).show();
    }

    @OnClick(R.id.add_measurement)
    void onAddMeasurementItem(final View view) {
        final String measurementString = mMeasurementEditText.getText().toString().trim();
        mPresenter.updateMeasurementValue(measurementString, mModel.getLastGeneratedDate());
    }

    @OnClick(R.id.measurement_date)
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
        mMeasurementDate.setText(formattedDate(measurementDate));
    }

    public View rootView() {
        return mRootView;
    }
}
