package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.db.Animals;

import java.util.List;

public class KindHandsAdapter extends RecyclerView.Adapter<KindHandsAdapter.LabelHolder> {

    private Context context;
    private List<Animals> data;

    public KindHandsAdapter(Context context, List<Animals> data) {
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

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class LabelHolder extends  RecyclerView.ViewHolder {
        public LabelHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

