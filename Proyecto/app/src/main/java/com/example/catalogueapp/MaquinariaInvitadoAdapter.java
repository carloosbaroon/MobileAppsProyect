package com.example.catalogueapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catalogueapp.database.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MaquinariaInvitadoAdapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recycler_item_view, parent, false);

        return new MaquinariaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MaquinariaViewHolder pHolder = (MaquinariaViewHolder)holder;
        if( products != null){
            Product p = products.get(position);
            pHolder.productName.setText(p.maquinariaName);
            Picasso.get().load(p.maquinariaImg).into(pHolder.recyclerImage);
            pHolder.recyclerRating.setText("Stars : "+p.maquinariaRanking);

        }else{
            pHolder.productName.setText("NONE");
        }
    }
    @Override
    public int getItemCount() {
        if( products != null)
            return products.size();
        return 0;
    }
    class MaquinariaViewHolder extends RecyclerView.ViewHolder{
        private TextView productName;
        private ImageView recyclerImage;
        private TextView recyclerRating;

        public int index;

        public MaquinariaViewHolder(View itemView){
            super(itemView);
            productName = itemView.findViewById(R.id.NameRecycler);
            recyclerImage = itemView.findViewById(R.id.ImageRecycler);
            recyclerRating = itemView.findViewById(R.id.RankingRecycler);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //index = getAdapterPosition();
                    callTo.sendRegisterMessage();
                }
            });
        }
    }
    private final LayoutInflater inflater;
    private List<Product> products;

    MainActivityInvitado callTo;

    public MaquinariaInvitadoAdapter(MainActivityInvitado context){
        inflater = LayoutInflater.from(context);
        callTo = context;
    }

    void setProducts(List<Product> products){
        this.products = products;
        notifyDataSetChanged();
    }
}
