package com.example.myapplication.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.db.Working;

import java.util.Collections;
import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.LabelHolder> {

    private Context context;
    private List<Working> data;

    public WorkAdapter(Context context, List<Working> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public WorkAdapter.LabelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.working_item, parent, false);
        return new LabelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkAdapter.LabelHolder holder, int position) {
        holder.name.setText(data.get(position).getName() +" " +data.get(position).getSurname().substring(0,1)+". ");
        holder.phone.setText(data.get(position).getPhone());
        holder.age.setText("Возраст: "+data.get(position).getAge());
        holder.work.setText(data.get(position).getWork());
        holder.city.setText("Город: "+data.get(position).getCity());
        holder.price.setText("Цена: " + data.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class LabelHolder extends RecyclerView.ViewHolder{

        TextView work, price, city, age, name, phone;

        public LabelHolder(@NonNull View itemView) {
            super(itemView);

            work = itemView.findViewById(R.id.work);
            price = itemView.findViewById(R.id.price);
            city = itemView.findViewById(R.id.city);
            age = itemView.findViewById(R.id.age);
            name = itemView.findViewById(R.id.name_user);
            phone = itemView.findViewById(R.id.num_user);
        }
    }
}
