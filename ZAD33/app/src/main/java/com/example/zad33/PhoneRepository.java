package com.example.zad33;

import android.app.Application;

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
        //repozytorium korzysta z DAO a nie bezpośrednio z bazy
        mPhoneDao = phoneRoomDatabase.phoneDao();
        mAllPhones = mPhoneDao.getAlphabetizedPhones();
    }
    LiveData<List<Phone>> getAllPhones() { return mAllPhones; }
    //operacje muszą być wykonywane w osobnym wątku
    void insert(Phone phone) {
        //Runnable -> lambda
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
