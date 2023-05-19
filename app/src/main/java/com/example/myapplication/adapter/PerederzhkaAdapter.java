package com.example.myapplication.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.db.Perederzhka;

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
