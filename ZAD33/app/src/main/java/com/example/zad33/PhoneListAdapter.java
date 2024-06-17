package com.example.zad33;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneListAdapter extends RecyclerView.Adapter<PhoneListAdapter.PhoneViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<Phone> mPhoneList;
    private SelectionTracker<Long> mSelectionTracker;
    private PhoneItemKeyProvider mPhoneItemKeyProvider;
    public PhoneListAdapter(Context context,
                            PhoneItemKeyProvider phoneItemKeyProvider) {
        mLayoutInflater=LayoutInflater.from(context);
        mPhoneItemKeyProvider=phoneItemKeyProvider;
        this.mPhoneList = null;
    }
    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView= mLayoutInflater
                .inflate(R.layout.recyclerview_item,parent,false);
        PhoneViewHolder phoneViewHolder=new PhoneViewHolder(rootView);
        return phoneViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {
        boolean isSelected=false;
        if ((mSelectionTracker != null) && mSelectionTracker
                .isSelected((long) mPhoneList.get(position).getId()))
            isSelected=true;
        holder.bindToPhoneViewHolder(position, isSelected);    }
    @Override
    public int getItemCount() {
        if (mPhoneList!=null){
            return mPhoneList.size();
        }
        return 0;
    }
    public class PhoneViewHolder extends RecyclerView.ViewHolder {
        TextView recyclerViewItemManufacturer;
        TextView recyclerViewItemModel;
        PhoneItemDetails phoneItemDetails;
        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerViewItemManufacturer=itemView.findViewById(R.id.recyclerViewItemManufacturer);
            recyclerViewItemModel=itemView.findViewById(R.id.recyclerViewItemModel);
            phoneItemDetails=new PhoneItemDetails();
        }
        public void bindToPhoneViewHolder(int position, boolean isSelected) {
            recyclerViewItemManufacturer.setText(mPhoneList.get(position).getManufacturer());
            recyclerViewItemModel.setText(mPhoneList.get(position).getModel());
            phoneItemDetails.id = mPhoneList.get(position).getId();
            phoneItemDetails.position=position;
            itemView.setActivated(isSelected);
        }
        public PhoneItemDetails getPhoneItemDetails() {
            return phoneItemDetails;
        }
    }
    public void setSelectionTracker(
            SelectionTracker<Long> mSelectionTracker) {
        this.mSelectionTracker = mSelectionTracker;
    }
    public void setPhoneList(List<Phone> phoneList) {
//        this.mPhoneList = phoneList;
//        notifyDataSetChanged();

        if (mSelectionTracker!=null)
            mSelectionTracker.clearSelection();
        this.mPhoneList = phoneList;
        mPhoneItemKeyProvider.setPhones(phoneList);
        notifyDataSetChanged();
    }
}

