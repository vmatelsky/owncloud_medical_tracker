package com.vlabs.medicinetracker;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TwoItemsViewHolder<F, S> extends ViewHolder {

    @Bind(R.id.first)
    TextView mFirst;

    @Bind(R.id.second)
    TextView mSecond;

    public TwoItemsViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(F first, S second) {
        mFirst.setText(first.toString());
        mSecond.setText(second.toString());
    }
}
