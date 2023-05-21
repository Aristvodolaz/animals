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
import com.example.myapplication.db.NeedPerderzhka;
import com.example.myapplication.db.Perederzhka;

import java.util.List;

public class NeedPerederzhkaAdapter extends RecyclerView.Adapter<NeedPerederzhkaAdapter.LabelHolder> {

    private Context context;
    private List<NeedPerderzhka> data;

    public NeedPerederzhkaAdapter(Context context, List<NeedPerderzhka> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public NeedPerederzhkaAdapter.LabelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.need_perederzhka_item, parent, false);
        return new LabelHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NeedPerederzhkaAdapter.LabelHolder holder, int position) {
        holder.sname.setText( data.get(position).getName() + " " + data.get(position).getSurname().substring(0, 1) + ". ");

        holder.phone.setText( data.get(position).getPhone());
        holder.type_animals.setText("Животное: " + data.get(position).getType_animals());
        holder.poroda_animals.setText("Порода: " + data.get(position).getPoroda_animals());
        holder.days.setText("Количество дней: " + data.get(position).getDays());
        holder.city.setText("Город: " + data.get(position).getCity());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class LabelHolder extends RecyclerView.ViewHolder {
        TextView name, surname, phone,  type_animals, poroda_animals, days, city, sname;

        public LabelHolder(@NonNull View itemView) {
            super(itemView);

//            name = itemView.findViewById(R.id.name_uesr);
            phone = itemView.findViewById(R.id.num_user);
            type_animals = itemView.findViewById(R.id.type_animals);
            poroda_animals = itemView.findViewById(R.id.poroda_animals);
            days = itemView.findViewById(R.id.days);
            city = itemView.findViewById(R.id.city);
            sname = itemView.findViewById(R.id.user);
        }
    }
}
