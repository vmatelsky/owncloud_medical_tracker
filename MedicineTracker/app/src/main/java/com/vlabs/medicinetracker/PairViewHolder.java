package com.vlabs.medicinetracker;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PairViewHolder<F, S> extends ViewHolder {

    @Bind(R.id.first)
    TextView mFirst;

    @Bind(R.id.second)
    TextView mSecond;

    public PairViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Pair<F, S> pair) {
        mFirst.setText(pair.first.toString());
        mSecond.setText(pair.second.toString());
    }
}
