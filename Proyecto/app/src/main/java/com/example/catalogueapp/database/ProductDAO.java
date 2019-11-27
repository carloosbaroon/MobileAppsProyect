package com.example.catalogueapp.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertProduct(Product... products);

    @Query ("UPDATE product SET businessName=:name, businessDescription=:desc, businessImage = :img WHERE maquinariaId = :id")
    public void updateWithoutRanking(String id, String name, String desc, String img);

    @Query("UPDATE product SET businessRanking=:ranking WHERE maquinariaId LIKE :id")
    public void updateRanking(String id, int ranking);

    @Query("SELECT * FROM product")
    public LiveData<List<Product>> getAll();

    @Query("SELECT * FROM product WHERE businessName LIKE :searchName")
    public LiveData<List<Product>> search(String searchName);

    @Query("SELECT count(*) FROM product")
    public int checkIfDatabaseNull();
}
