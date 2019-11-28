package com.example.catalogueapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.catalogueapp.database.RankingUpdateTask;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private Button maps;

    ProductViewModel businessesModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        updateShownData();

        businessesModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        maps = (Button)findViewById(R.id.mapButton);

        maps.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view){
                Intent intent = new Intent(DetailActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateShownData(){

        final String id = getIntent().getExtras().getString("maquinariaId");

        final String name = getIntent().getExtras().getString("maquinariaName");
        TextView nameView = findViewById(R.id.maquinariaName);
        nameView.setText(name);

        final String desc = getIntent().getExtras().getString("maquinariaDesc");
        TextView descView = findViewById(R.id.businessDesc);
        descView.setText(desc);

        final String img = getIntent().getExtras().getString("maquinariaImg");
        ImageView image = findViewById(R.id.logoView);
        Picasso.get().load(img).into(image);

        final int ranking = getIntent().getExtras().getInt("maquinariaRanking");
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
