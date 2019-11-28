package com.example.catalogueapp.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="MaquinariaId")
    public String maquinariaId;

    @ColumnInfo(name = "MaquinariaName")
    public String maquinariaName;

    @ColumnInfo(name = "MaquinariaDesc")
    public String maquinariaDesc;

    @ColumnInfo(name="MaquinariaImg")
    public String maquinariaImg;

    @ColumnInfo(name="MaquinariaRanking")
    public int maquinariaRanking;

    @ColumnInfo(name="MaquinariaLat")
    public String maquinariaLat;

    @ColumnInfo(name="MaquinariaLong")
    public String maquinariaLong;
}
