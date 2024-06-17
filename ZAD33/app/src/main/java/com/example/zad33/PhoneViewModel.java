package com.example.zad33;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneViewModel  extends AndroidViewModel {
    private final PhoneRepository mRepository;
    private final LiveData<List<Phone>> mAllPhoness;

    public PhoneViewModel(@NonNull Application application) {
        super(application);
        mRepository = new PhoneRepository(application);
        mAllPhoness = mRepository.getAllPhones();
    }
    LiveData<List<Phone>> getAllPhones() { return mAllPhoness; }
    public void insert(Phone phone) {
        mRepository.insert(phone);
    }
    public void deleteAll() {
        mRepository.deleteAll();
    }
    public void deletePhone(Phone phone) {
        mRepository.deleteWord(phone);
    }
}