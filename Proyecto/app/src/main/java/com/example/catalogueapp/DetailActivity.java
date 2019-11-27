package com.example.catalogueapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.catalogueapp.database.RankingUpdateTask;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    ProductViewModel businessesModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        updateShownData();

        businessesModel = ViewModelProviders.of(this).get(ProductViewModel.class);
    }

    private void updateShownData(){

        final String id = getIntent().getExtras().getString("maquinariaId");

        final String name = getIntent().getExtras().getString("businessName");
        TextView nameView = findViewById(R.id.businessName);
        nameView.setText(name);

        final String desc = getIntent().getExtras().getString("businessDescription");
        TextView descView = findViewById(R.id.businessDesc);
        descView.setText(desc);

        final String img = getIntent().getExtras().getString("businessImage");
        ImageView image = findViewById(R.id.logoView);
        Picasso.get().load(img).into(image);

        final int ranking = getIntent().getExtras().getInt("businessRanking");
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating(ranking);



        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                RankingUpdateTask rankingTask = new RankingUpdateTask(getApplicationContext(), null, id, (int) ratingBar.getRating());
                rankingTask.execute();
            }
        });
    }
}
