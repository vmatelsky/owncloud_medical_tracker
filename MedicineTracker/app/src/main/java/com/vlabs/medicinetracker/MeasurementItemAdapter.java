package com.vlabs.medicinetracker;

import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vlabs.medicinetracker.units.domain.MeasurementItem;

import java.util.Date;
import java.util.List;

/**
 * Created by vlad on 3/10/16.
 */
public class MeasurementItemAdapter<Unit> extends RecyclerView.Adapter<TwoItemsViewHolder<Unit, Date>> {

    private final List<MeasurementItem<Unit>> mData;

    public MeasurementItemAdapter(final List<MeasurementItem<Unit>> data) {
        mData = data;
    }

    @Override
    public TwoItemsViewHolder<Unit, Date> onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pair_item_layout, parent, false);
        return new TwoItemsViewHolder<>(itemView);
    }
    @Override
    public void onBindViewHolder(final TwoItemsViewHolder<Unit, Date> holder, final int position) {
        final MeasurementItem<Unit> item = mData.get(position);
        holder.bind(item.getUnit(), item.getDate());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
