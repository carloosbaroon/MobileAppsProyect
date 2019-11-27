package com.example.catalogueapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.catalogueapp.database.CatalogueDatabase;
import com.example.catalogueapp.database.DatabaseReceiver;
import com.example.catalogueapp.database.DatabaseTask;
import com.example.catalogueapp.database.Product;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DatabaseReceiver {

    //public static String MESSAGE = "com.example.catalogueApp.MainActivity";
    ProductViewModel products;
    ProductCatalogueAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        products = ViewModelProviders.of(this).get(ProductViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter= new ProductCatalogueAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void actionFromButton(View view){
        Log.d("CLICKED" , "FROM VIEW " + view);
    }

    public void getAll(List<Product> products){

    }
    public void doAction(View view){
        String src = "%"+((EditText)findViewById(R.id.searchText)).getText()+"%";
        products.searchProducts(getApplicationContext(),src).observe(this,
                new Observer<List<Product>>() {
                    @Override
                    public void onChanged(List<Product> products) {
                        adapter.setProducts(products);
                    }
                });
    }

    public void goToVolleyScreen(View view){

        Intent goToNextActivity = new Intent(getApplicationContext(), VolleyActivity.class);
        startActivity(goToNextActivity);
    }

    public void goToService(View view){

        Intent goToNextActivity = new Intent(getApplicationContext(), DoServiceActivity.class);
        startActivity(goToNextActivity);
    }

    public void sendToNextScreen(final String name) {
        final Intent intent = new Intent(this, DetailActivity.class);

        products.searchProducts(getApplicationContext(),name).observe(this,
                new Observer<List<Product>>() {
                    @Override
                    public void onChanged(List<Product> companies) {
                        intent.putExtra("maquinariaId", companies.get(0).maquinariaId);
                        intent.putExtra("businessName", companies.get(0).businessName);
                        intent.putExtra("businessDescription", companies.get(0).businessDescription);
                        intent.putExtra("businessImage", companies.get(0).businessImage);
                        intent.putExtra("businessRanking", companies.get(0).businessRanking);
                        startActivity(intent);
                    }
                });

    }



}
