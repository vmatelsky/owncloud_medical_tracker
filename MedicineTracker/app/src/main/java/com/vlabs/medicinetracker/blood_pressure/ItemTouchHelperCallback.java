package com.vlabs.medicinetracker.blood_pressure;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.vlabs.medicinetracker.blood_pressure.show.BloodPressureAdapter;

/**
 * Created by vlad on 3/24/16.
 */
public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final BloodPressureAdapter mAdapter;

    public ItemTouchHelperCallback(final BloodPressureAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(final RecyclerView recyclerView, final ViewHolder viewHolder) {
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(0, swipeFlags);
    }

    @Override
    public boolean onMove(final RecyclerView recyclerView, final ViewHolder viewHolder, final ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(final ViewHolder viewHolder, final int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
