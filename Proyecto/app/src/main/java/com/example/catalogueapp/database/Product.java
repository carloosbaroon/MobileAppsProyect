package com.example.catalogueapp.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="businessId")
    public String businessId;

    @ColumnInfo(name = "businessName")
    public String businessName;

    @ColumnInfo(name = "businessDescription")
    public String businessDescription;

    @ColumnInfo(name="businessImage")
    public String businessImage;

    @ColumnInfo(name="businessRanking")
    public int businessRanking;
}
