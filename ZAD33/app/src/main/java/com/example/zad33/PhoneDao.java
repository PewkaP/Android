package com.example.zad33;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PhoneDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Phone phone);

    @Query("DELETE FROM phone_table")
    void deleteAll();

    @Query("SELECT * FROM phone_table")
    LiveData<List<Phone>> getAlphabetizedPhones();

    @Delete
    void deletePhone(Phone phone);
}
