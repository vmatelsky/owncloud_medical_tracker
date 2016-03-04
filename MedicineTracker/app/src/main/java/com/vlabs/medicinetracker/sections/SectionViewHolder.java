package com.vlabs.medicinetracker.sections;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vlabs.medicinetracker.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vlad on 04/03/16.
 */
public class SectionViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.title)
    TextView mTitle;

    public SectionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final SectionItem item, final SectionsAdapter.SectionItemClickListener clickListener) {
        mTitle.setText(item.getTitleId());
        itemView.setOnClickListener(v -> clickListener.onSectionItemClicked(item));
    }
}
