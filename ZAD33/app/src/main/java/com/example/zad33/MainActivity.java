//package com.example.zad33;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.Toast;
//
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.contract.ActivityResultContracts;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//public class MainActivity extends AppCompatActivity {
//    private PhoneViewModel mPhoneViewModel;
//    private PhoneListAdapter mAdapter;
//    private FloatingActionButton mMainFab;
//    private ActivityResultLauncher<Intent> mActivityResultLauncher;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mActivityResultLauncher = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                result -> {
//                    Log.d(TAG, "onActivityResult: Result received");
//                    try {
//                        if (result.getResultCode() == RESULT_OK) {
//                            Intent data = result.getData();
//                            Bundle extras = data.getExtras();
//                            if (extras != null) {
//                                String manufacturer = extras.getString(NewPhoneActivity.EXTRA_REPLY_MANUFACTURER);
//                                String model = extras.getString(NewPhoneActivity.EXTRA_REPLY_MODEL);
//                                String androidVersion = extras.getString(NewPhoneActivity.EXTRA_REPLY_ANDROID_VERSION);
//                                String webpage = extras.getString(NewPhoneActivity.EXTRA_REPLY_WEBPAGE);
//
//                                if (manufacturer != null && model != null && androidVersion != null && webpage != null) {
//                                    Log.d(TAG, "onActivityResult: phone.manufacturer: " + manufacturer);
//                                    Log.d(TAG, "onActivityResult: phone.model: " + model);
//                                    Log.d(TAG, "onActivityResult: phone.android: " + androidVersion);
//                                    Log.d(TAG, "onActivityResult: phone.webpage: " + webpage);
//                                    Phone phone = new Phone(manufacturer, model, androidVersion, webpage);
//                                    Log.d(TAG, "onActivityResult: Inserting phone: " + phone);
//                                    Log.d(TAG, "onActivityResult: phone.manufacturer: " + phone.getManufacturer());
//                                    Log.d(TAG, "onActivityResult: phone.model: " + phone.getModel());
//                                    Log.d(TAG, "onActivityResult: phone.android: " + phone.getAndroidVersion());
//                                    Log.d(TAG, "onActivityResult: phone.webpage: " + phone.getWebpage());
//                                    mPhoneViewModel.insert(phone);
//                                    Log.d(TAG, "onActivityResult: Phone inserted");
//                                } else {
//                                    Log.e(TAG, "onActivityResult: One or more phone details are null");
//                                }
//                            } else {
//                                Log.e(TAG, "onActivityResult: Intent extras are null");
//                            }
//                        }
//                    } catch (Exception e) {
//                        Log.e(TAG, "onActivityResult: Exception occurred", e);
//                    }
//                });
//
//        mMainFab = findViewById(R.id.fabMain);
//        mMainFab.setOnClickListener(view -> {
//            Log.d(TAG, "onClick: FAB clicked");
//            mainFabClicked();
//        });
//
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//
//        mAdapter = new PhoneListAdapter(this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(mAdapter);
//        mPhoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);
//        mPhoneViewModel.getAllPhones().observe(this, phones -> {
//            Log.d(TAG, "onChanged: Phone list updated, size: " + phones.size());
//            mAdapter.setPhoneList(phones);
//            for (Phone phone : phones) {
//                Log.d(TAG, "onChanged: Phone: " + phone);
//            }
//
//        });
//
//
//
//    }
//
//    private void mainFabClicked() {
//        Log.d(TAG, "mainFabClicked: Launching NewPhoneActivity");
//        Intent intent = new Intent(MainActivity.this, NewPhoneActivity.class);
//        mActivityResultLauncher.launch(intent);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.clear_data) {
//            Toast.makeText(this, "Clearing the data...", Toast.LENGTH_SHORT).show();
//            mPhoneViewModel.deleteAll();
//            Log.d(TAG, "onOptionsItemSelected: Data cleared");
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}



package com.example.zad33;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.selection.Selection;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PhoneViewModel mPhoneViewModel;
    private PhoneListAdapter mAdapter;
    private FloatingActionButton mMainFab;
    private SelectionTracker<Long> mSelectionTracker;
    private FloatingActionButton mDeleteFab;
    private boolean mIsMainFabAdd = true;
    private PhoneItemKeyProvider mPhoneItemKeyProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        mPhoneItemKeyProvider = new PhoneItemKeyProvider();
        mAdapter = new PhoneListAdapter(this,mPhoneItemKeyProvider);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPhoneViewModel = new ViewModelProvider(this)
                .get(PhoneViewModel.class);
        mPhoneViewModel.getAllPhones().observe(this, phones -> {
            mAdapter.setPhoneList(phones);
        });
        mActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Phone phone = new Phone(
                                result.getData().getStringExtra(NewPhoneActivity.EXTRA_REPLY_MANUFACTURER),
                                result.getData().getStringExtra(NewPhoneActivity.EXTRA_REPLY_MODEL),
                                result.getData().getStringExtra(NewPhoneActivity.EXTRA_REPLY_ANDROID_VERSION),
                                result.getData().getStringExtra(NewPhoneActivity.EXTRA_REPLY_WEBPAGE)
                        );
                        mPhoneViewModel.insert(phone);
                    }
                });
        mMainFab = findViewById(R.id.fabMain);
        mMainFab.setOnClickListener(view -> mainFabClicked());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mSelectionTracker = new SelectionTracker.Builder<>(

                "phone-selection",
                recyclerView,
                mPhoneItemKeyProvider,
                //odczytuje szczegóły wybranego elementu
                new PhoneItemDetailsLookup(recyclerView),
                //magazyn na klucze
                StorageStrategy.createLongStorage()).build();
        mAdapter.setSelectionTracker(mSelectionTracker);
        //...
        //obsługa przycisku FAB kasowania zaznaczonych elementów
        mDeleteFab = findViewById(R.id.fabDelete);
        mDeleteFab.setOnClickListener(view -> deleteSelection());
        //selection tracker będzie informował o zmianach zaznaczenia
        mSelectionTracker.addObserver(
                new SelectionTracker.SelectionObserver<Long>() {
                    @Override
                    public void onSelectionChanged() {
                        //wyświetlamy/chowamy przycisk kasowania
                        updateFabs();
                        super.onSelectionChanged();
                    }
                    @Override
                    public void onSelectionRestored() {
                        //wyświetlamy/chowamy przycisk kasowania
                        updateFabs();
                        super.onSelectionRestored();
                    }
                    @Override
                    public void onItemStateChanged(@NonNull Long key,
                                                   boolean selected) {
                        super.onItemStateChanged(key, selected);
                    }
                }
        );
    }
    ActivityResultLauncher<Intent> mActivityResultLauncher;
    private void mainFabClicked() {
//        Intent intent = new Intent(
//                MainActivity.this, NewPhoneActivity.class);
//        mActivityResultLauncher.launch(intent);
        if (mIsMainFabAdd) {
            Intent intent = new Intent(
                    MainActivity.this, NewPhoneActivity.class);
            mActivityResultLauncher.launch(intent);
        } else {
            mSelectionTracker.clearSelection();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.clear_data) {
            Toast.makeText(this,"Clearing the data...",
                    Toast.LENGTH_SHORT).show();
            mPhoneViewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void deleteSelection() {
        Selection<Long> selection=mSelectionTracker.getSelection();
        int phonePosition=-1;
        List<Phone> phoneList=mPhoneViewModel.getAllPhones().getValue();
        //przeglądamy identyfikatory z zaznaczenia i kasujemy elementy
        for (long phoneId:selection) {
            phonePosition=mPhoneItemKeyProvider.getPosition(phoneId);
            mPhoneViewModel.deletePhone(phoneList.get(phonePosition));
        }
    }
    private void updateFabs() {
        if (mSelectionTracker.hasSelection())
        {
            mDeleteFab.setVisibility(View.VISIBLE);
            mMainFab.setImageDrawable(
                    getDrawable(R.drawable.ic_baseline_cancel_24));
            mIsMainFabAdd=false;
        }else {
            mDeleteFab.setVisibility(View.GONE);
            mMainFab.setImageDrawable(
                    getDrawable(R.drawable.ic_baseline_add_24));
            mIsMainFabAdd=true;
        }
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //stan zaznaczenia trzeba zachować
        mSelectionTracker.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(
            @NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //stan zaznaczenia trzeba przywrócić
        mSelectionTracker.onRestoreInstanceState(savedInstanceState);
    }







}

