package com.example.myapplication.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class LabelHolder extends RecyclerView.ViewHolder{
        public LabelHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
