package com.example.zad33;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneListAdapter extends RecyclerView.Adapter<PhoneListAdapter.PhoneViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<Phone> mPhoneList;
    public PhoneListAdapter(Context context) {
        mLayoutInflater=LayoutInflater.from(context);
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
        holder.bindToPhoneViewHolder(position);
    }
    @Override
    public int getItemCount() {
        if (mPhoneList!=null)
            return mPhoneList.size();
        return 0;
    }
    public class PhoneViewHolder extends RecyclerView.ViewHolder {
        TextView phoneTextView;
        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);
            phoneTextView=itemView.findViewById(R.id.phoneTextView);
        }
        public void bindToPhoneViewHolder(int position)
        {
            phoneTextView.setText(mPhoneList.get(position).getManufacturer());
        }
    }
    public void setPhoneList(List<Phone> phoneList) {
        this.mPhoneList = phoneList;
        notifyDataSetChanged();
    }
}

