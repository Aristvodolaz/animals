package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.db.NeedWorking;

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

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class LableHolder extends RecyclerView.ViewHolder {
        public LableHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
