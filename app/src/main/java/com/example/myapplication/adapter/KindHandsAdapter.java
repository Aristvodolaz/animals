package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.db.Animals;
import com.example.myapplication.db.AnimalsLost;
import com.squareup.picasso.Picasso;

import java.util.List;

public class KindHandsAdapter extends RecyclerView.Adapter<KindHandsAdapter.LabelHolder> {

    private Context context;
    private List<AnimalsLost> data;

    public KindHandsAdapter(Context context, List<AnimalsLost> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public KindHandsAdapter.LabelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.kind_hands_item, parent, false);
        return new LabelHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KindHandsAdapter.LabelHolder holder, int position) {
        holder.name.setText("Кличка: " + data.get(position).getName() +" (" +data.get(position).getPol()+")");
        holder.poroda.setText("Порода: "+ data.get(position).getPoroda());
        holder.descriprion.setText("Описание: "+data.get(position).getDescription());
        holder.address.setText("Адрес пропажи: "+data.get(position).getAdress());
        holder.datePropazhi.setText("Дата пропажи: "+data.get(position).getDate_prodazhi());
        holder.userName.setText(data.get(position).getNameUser());
        holder.userPhone.setText(data.get(position).getPhoneUser());
//        Picasso.get().load(data.get(position).getImgURL()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class LabelHolder extends  RecyclerView.ViewHolder {
        TextView address, name, datePropazhi, descriprion, poroda, userName, userPhone;
        ImageView img;
        public LabelHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.address);
            name = itemView.findViewById(R.id.name);
            datePropazhi  = itemView.findViewById(R.id.date_propazhi);
            descriprion = itemView.findViewById(R.id.description);
            poroda = itemView.findViewById(R.id.poroda);
            img = itemView.findViewById(R.id.img);
            userName = itemView.findViewById(R.id.name_user);
            userPhone = itemView.findViewById(R.id.user_phone);
        }
    }
}

