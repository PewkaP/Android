package com.example.zad33;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.RoomDatabase;

import java.util.List;

public class PhoneRepository {
    private PhoneDao mPhoneDao;
    private LiveData<List<Phone>> mAllPhones;
    PhoneRepository(Application application) {
        PhoneRoomDatabase phoneRoomDatabase =
                PhoneRoomDatabase.getDatabase(application);
        mPhoneDao = phoneRoomDatabase.phoneDao();
        mAllPhones = mPhoneDao.getAlphabetizedPhones();
    }
    LiveData<List<Phone>> getAllPhones() { return mAllPhones; }
    void insert(Phone phone) {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPhoneDao.insert(phone);
        });
    }
    void deleteAll() {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPhoneDao.deleteAll();
        });
    }
    void deleteWord(Phone phone)
    {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPhoneDao.deletePhone(phone);
        });
    }
}
