package com.example.catalogueapp.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import java.util.List;

public class RankingUpdateTask extends AsyncTask<Product,Void, List<Product>> {
    CatalogueDatabase db;
    DatabaseReceiver receiver;

    String id;
    int rating;

    public RankingUpdateTask(Context ctx , DatabaseReceiver receiver, String maquinariaId, int businessRating){
        this.receiver = receiver;
        db = Room.databaseBuilder(ctx,
                CatalogueDatabase.class ,
                "catalogue-database").build();

        id = maquinariaId;
        rating = businessRating;
    }

    @Override
    public void onPreExecute(){
    }
    @Override
    protected List<Product> doInBackground(Product... params) {
        db.productDao().updateRanking(id, rating);
        return null;
    }
    @Override
    public void onPostExecute(List<Product> result){}
}
