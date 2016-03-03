package com.vlabs.medicinetracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by vlad on 04/03/16.
 */
public class SectionsAdapter extends RecyclerView.Adapter<SectionViewHolder> {

    public interface SectionItemClickListener {
        void onSectionItemClicked(SectionItem item);
    }

    private final List<SectionItem> mData;
    private final SectionItemClickListener mClickListener;

    public SectionsAdapter(List<SectionItem> items, SectionItemClickListener clickListener) {
        mData = items;
        mClickListener = clickListener;
    }


    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_item_layout, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        holder.bind(mData.get(position), mClickListener);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
