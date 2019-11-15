package com.example.catalogueapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.catalogueapp.database.DatabaseTask;
import com.example.catalogueapp.database.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VolleyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        getData();
    }

    public void returnToList(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void getData() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8000";
        final DatabaseTask databaseTask = new DatabaseTask(getApplicationContext(), null);
        final TextView contentText = findViewById(R.id.textView);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("Businesses");
                            Product[] businesses = new Product[jsonArray.length()];
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject b = jsonArray.getJSONObject(i);

                                String id = b.getString("BusinessId");
                                String name = b.getString("BusinessName");
                                String desc = b.getString("BusinessDescription");
                                String image = b.getString("BusinessImage");

                                Product business = new Product();
                                business.businessId = id;
                                business.businessName = name;
                                business.businessDescription = desc;
                                business.businessImage = image;
                                business.businessRanking = 3;

                                businesses[i] = business;

                                contentText.append(business.businessName+"\n\n");


                            }
                            databaseTask.execute(businesses);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(request);
    }
}
