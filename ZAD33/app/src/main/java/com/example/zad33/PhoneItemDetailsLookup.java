package com.example.zad33;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

public class PhoneItemDetailsLookup extends ItemDetailsLookup<Long> {
    private RecyclerView mRecyclerView;
    public PhoneItemDetailsLookup(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }
    @Nullable
    @Override
    public ItemDetails<Long> getItemDetails(@NonNull MotionEvent e) {
        //ustala szczegóły zaznaczonego elementu (na podstawie x i y)
        View view=
                mRecyclerView.findChildViewUnder(e.getX(),e.getY());
        if (view !=null)
        {
            RecyclerView.ViewHolder viewHolder=
                    mRecyclerView.getChildViewHolder(view);
            if (viewHolder instanceof PhoneListAdapter.PhoneViewHolder)
                return ((PhoneListAdapter.PhoneViewHolder) viewHolder)
                        .getPhoneItemDetails();
        }
        return null;
    }}