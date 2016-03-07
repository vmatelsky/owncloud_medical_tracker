package com.vlabs.medicinetracker.singleMeasurement.mvp;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.vlabs.medicinetracker.R;

import java.util.Date;

/**
 * Created by vlad on 3/8/16.
 */
public class SMPresenter<Unit> {

    private final SMModel<Unit> mModel;
    private final SMView<Unit> mView;
    private final Converter<String, Unit> mConverter;

    public SMPresenter(
            final Converter<String, Unit> converter,
            final Context context) {
        mConverter = converter;
        mModel = new SMModel<>();
        mView = new SMView<>(context, this, mModel);
    }

    public String measurementTitle(final Context context) {
        return context.getResources().getString(R.string.height);
    }

    public void updateMeasurementValue(final String newHeight, final Date withDate) {
        if (TextUtils.isEmpty(newHeight)) {
            mView.notifyError("please specify height");
        } else {
            mModel.addMeasurementValue(mConverter.convert(newHeight), withDate);
        }
    }

    public void setMeasurementDate(final Date measurementDate) {
        mModel.setMeasurementDate(measurementDate);
    }

    public View getView() {
        return mView.rootView();
    }
}
