package com.example.zad33;

import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemKeyProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneItemKeyProvider extends ItemKeyProvider<Long> {
    private Map<Long,Integer> mKeyToPosition;
    private List<Phone> mPhoneList;
    public PhoneItemKeyProvider() {
        super(SCOPE_MAPPED);
        mPhoneList=null;
    }
    public void setPhones(List<Phone> phoneList)
    {
        this.mPhoneList = phoneList;
        mKeyToPosition=new HashMap<>(mPhoneList.size());
        for (int i=0;i<mPhoneList.size();++i)
            mKeyToPosition.put((long) mPhoneList.get(i).getId(),i);
    }
    @Nullable
    @Override
    public Long getKey(int position) {
        return (long) mPhoneList.get(position).getId();
    }
    @Override
    public int getPosition(Long key) { return mKeyToPosition.get(key); }
}