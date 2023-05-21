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
import com.example.myapplication.db.Perederzhka;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PerederzhkaAdapter extends RecyclerView.Adapter<PerederzhkaAdapter.LabelHolder> {
    private Context context;
    private List<Perederzhka> data;

    public PerederzhkaAdapter(Context context, List<Perederzhka> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public PerederzhkaAdapter.LabelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.perederzhka_item, parent, false);
        return new LabelHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PerederzhkaAdapter.LabelHolder holder, int position) {
        holder.name.setText( data.get(position).getName() + " " + data.get(position).getSurname().substring(0, 1) + ". ");
        holder.city.setText("Город: "+data.get(position).getCity());
        holder.phone.setText(data.get(position).getPhone());
        holder.type_animals.setText("Тип животного: " + data.get(position).getType_animals());
        holder.description.setText("Описание: " + data.get(position).getDescription());
        holder.price.setText("Стоимость: " + data.get(position).getPrice());
        if (!data.get(position).getImgURL().equals("") || data.get(position).getImgURL() != null) {
            Picasso.get().load(data.get(position).getImgURL()).into(holder.img);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class LabelHolder extends RecyclerView.ViewHolder {
        TextView name, surname, phone, city, type_animals, description, price;
        ImageView img;

        public LabelHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_user);
            phone = itemView.findViewById(R.id.num_user);
            city = itemView.findViewById(R.id.city);
            type_animals = itemView.findViewById(R.id.type_animals);
            description = itemView.findViewById(R.id.description);
            img = itemView.findViewById(R.id.img);
            price = itemView.findViewById(R.id.price);
        }
    }
}