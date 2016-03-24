package com.vlabs.medicinetracker.blood_pressure;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vlabs.medicinetracker.R;
import com.vlabs.medicinetracker.TwoItemsViewHolder;
import com.vlabs.medicinetracker.db.BloodPressureMeasurement;
import com.vlabs.medicinetracker.db.BloodPressureMeasurementModel;
import com.vlabs.medicinetracker.units.domain.BloodPressure;

import java.util.Date;
import java.util.List;

/**
 * Created by vlad on 3/24/16.
 */
public class BloodPressureAdapter extends
        RecyclerView.Adapter<TwoItemsViewHolder<BloodPressure, Date>>
        implements ItemTouchHelperAdapter {

    private final List<BloodPressureMeasurement> mData;
    private final BloodPressureMeasurementModel mModel;

    public BloodPressureAdapter(final BloodPressureMeasurementModel model,
                                final List<BloodPressureMeasurement> data) {
        mModel = model;
        mData = data;
    }

    @Override
    public TwoItemsViewHolder<BloodPressure, Date> onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pair_item_layout, parent, false);
        return new TwoItemsViewHolder<>(itemView);
    }

    @Override
    public void onBindViewHolder(final TwoItemsViewHolder<BloodPressure, Date> holder, final int position) {
        final BloodPressureMeasurement item = mData.get(position);
        holder.bind(new BloodPressure(item.systolic, item.diastolic), item.measureDate);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onItemDismiss(final int position) {
        mModel.remove(mData.get(position));
        mData.remove(position);
        notifyItemRemoved(position);
    }
}
