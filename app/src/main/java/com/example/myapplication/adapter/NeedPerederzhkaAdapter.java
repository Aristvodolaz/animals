package com.example.myapplication.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class LabelHolder extends RecyclerView.ViewHolder {
        public LabelHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
