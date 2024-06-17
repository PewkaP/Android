package com.example.zad33;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "phone_table")
public class Phone {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NotNull
    @ColumnInfo(name = "manufacturer")
    private String manufacturer;
    @NotNull
    @ColumnInfo(name = "model")
    private String model;
    @NotNull
    @ColumnInfo(name = "androidVersion")
    private String androidVersion;
    @NotNull
    @ColumnInfo(name = "webpage")
    private String webpage;
    public Phone(@NotNull String manufacturer, @NotNull String model, @NotNull String androidVersion, @NotNull String webpage){
        this.manufacturer=manufacturer;
        this.model=model;
        this.androidVersion=androidVersion;
        this.webpage=webpage;
    }
    @NotNull
    public int getId() {
        return id;
    }

    public void setId(@NotNull int id) {
        this.id = id;
    }
    @NotNull
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(@NotNull String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @NotNull
    public String getModel() {
        return model;
    }

    public void setModel(@NotNull String model) {
        this.model = model;
    }

    @NotNull
    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(@NotNull String androidVersion) {
        this.androidVersion = androidVersion;
    }

    @NotNull
    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(@NotNull String webpage) {
        this.webpage = webpage;
    }
}
