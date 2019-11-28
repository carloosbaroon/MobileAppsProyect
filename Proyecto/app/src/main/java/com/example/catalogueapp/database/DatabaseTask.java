package com.example.catalogueapp.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import java.util.List;

public class DatabaseTask extends AsyncTask<Product,Void, List<Product>> {
    CatalogueDatabase db;
    DatabaseReceiver receiver;
    public DatabaseTask(Context ctx , DatabaseReceiver receiver){
        this.receiver = receiver;
        db = Room.databaseBuilder(ctx,
                CatalogueDatabase.class ,
                "catalogue-database").build();
    }

    @Override
    public void onPreExecute(){
    }
    @Override
    protected List<Product> doInBackground(Product... params) {

        if(db.productDao().checkIfDatabaseNull() > 0)
        {
            db.productDao().insertProduct(params);

        }
        else
        {
            for(int i = 0; i < params.length; i++){
                db.productDao().updateWithoutRanking(
                        params[i].businessId,
                        params[i].businessName,
                        params[i].businessDescription,
                        params[i].businessImage);
            }

        }

        return null;
    }
    @Override
    public void onPostExecute(List<Product> result){
    }
}

