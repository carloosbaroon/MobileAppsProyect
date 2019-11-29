package com.example.catalogueapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.catalogueapp.database.DatabaseReceiver;
import com.example.catalogueapp.database.Product;

import java.util.List;

public class MainActivityInvitado extends AppCompatActivity implements DatabaseReceiver {

    //public static String MESSAGE = "com.example.catalogueApp.MainActivity";
    ProductViewModel products;
    MaquinariaInvitadoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_invitado);

        products = ViewModelProviders.of(this).get(ProductViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter= new MaquinariaInvitadoAdapter(this);
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

    public void goToRegistro(View view){

        Intent goToNextActivity = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(goToNextActivity);
    }

    public void sendRegisterMessage(){
        Toast.makeText(MainActivityInvitado.this,"Registraté para ver detalles",Toast.LENGTH_SHORT).show();
    }
}
