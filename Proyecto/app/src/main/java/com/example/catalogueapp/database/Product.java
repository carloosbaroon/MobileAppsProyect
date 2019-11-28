package com.example.catalogueapp.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="maquinariaId")
    public String maquinariaId;

    @ColumnInfo(name = "maquinariaName")
    public String maquinariaName;

    @ColumnInfo(name = "maquinariaDesc")
    public String maquinariaDesc;

    @ColumnInfo(name="maquinariaImg")
    public String maquinariaImg;

    @ColumnInfo(name="maquinariaRanking")
    public int maquinariaRanking;
}
