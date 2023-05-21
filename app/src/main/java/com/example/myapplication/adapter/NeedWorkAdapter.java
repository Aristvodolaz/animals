package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.db.NeedWorking;

import org.w3c.dom.Text;

import java.util.List;

public class NeedWorkAdapter extends RecyclerView.Adapter<NeedWorkAdapter.LableHolder> {

    private Context context;
    List<NeedWorking> data;

    public NeedWorkAdapter(Context context, List<NeedWorking> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public NeedWorkAdapter.LableHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.work_need_item, parent, false);
        return new LableHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NeedWorkAdapter.LableHolder holder, int position) {
        holder.address.setText("Адрес: "+ data.get(position).getAddress());
        holder.animals.setText("Животное: "+data.get(position).getAnimals());
        holder.poroda.setText("Порода: " + data.get(position).getPoroda());
        holder.days.setText("Количество дней: "+ data.get(position).getDays());
        holder.name.setText(data.get(position).getName() + " "+data.get(position).getSurname().substring(0,1)+". ");
        holder.phone.setText(data.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class LableHolder extends RecyclerView.ViewHolder {

        TextView address, name, phone, animals, poroda,days;
        public LableHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.address);
            animals = itemView.findViewById(R.id.animals);
            poroda = itemView.findViewById(R.id.poroda);
            days = itemView.findViewById(R.id.days);
            name = itemView.findViewById(R.id.name_user);
            phone = itemView.findViewById(R.id.num_user);

        }
    }
}
