package com.vlabs.medicinetracker;

import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AddedPairsAdapter extends RecyclerView.Adapter<PairViewHolder<String, String>> {

    private final List<Pair<String, String>> mData;

    public AddedPairsAdapter(final List<Pair<String, String>> data) {
        mData = data;
    }

    @Override
    public PairViewHolder<String, String> onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pair_item_layout, parent, false);
        return new PairViewHolder<>(itemView);
    }
    @Override
    public void onBindViewHolder(final PairViewHolder<String, String> holder, final int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
