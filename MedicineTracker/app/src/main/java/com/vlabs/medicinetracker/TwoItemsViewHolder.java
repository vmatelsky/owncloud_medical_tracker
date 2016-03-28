package com.vlabs.medicinetracker;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;

import com.vlabs.medicinetracker.blood_pressure.BloodPressureAddDialog;
import com.vlabs.medicinetracker.blood_pressure.BloodPressureAddDialog.Listener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TwoItemsViewHolder<F, S> extends ViewHolder {

    public interface Listener {
        boolean onLongClick(final int position);

        void onClick(final int position);
    }

    @Bind(R.id.first)
    TextView mFirst;

    @Bind(R.id.second)
    TextView mSecond;

    public TwoItemsViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(F first, S second, Listener listener, int position) {
        itemView.setOnLongClickListener(v -> listener.onLongClick(position));
        itemView.setOnClickListener(v -> listener.onClick(position));

        mFirst.setText(first.toString());
        mSecond.setText(second.toString());
    }
}
