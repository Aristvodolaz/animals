package com.example.myapplication.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.db.Animals;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PoteryashkiAnimalsAdapter extends RecyclerView.Adapter<PoteryashkiAnimalsAdapter.LabelHolder> {

    private Context context;
    private List<Animals> data;
    public PoteryashkiAnimalsAdapter(Context context, List<Animals> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public PoteryashkiAnimalsAdapter.LabelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.poteryashki_item, parent, false);
        return new LabelHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PoteryashkiAnimalsAdapter.LabelHolder holder, int position) {
        holder.pol.setText(data.get(position).getPol());
        holder.description.setText("Особые приметы: "+data.get(position).getDescription());
        holder.address.setText("Место нахождения: "+ data.get(position).getAdress());
        Picasso.get().load(data.get(position).getImgURL()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class LabelHolder  extends  RecyclerView.ViewHolder{

        ImageView img;
        TextView pol, description, address;
        public LabelHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            pol = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            address = itemView.findViewById(R.id.address);
        }
    }
}
