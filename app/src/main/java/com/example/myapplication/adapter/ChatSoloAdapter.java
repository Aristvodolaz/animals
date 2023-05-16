package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.R;
import com.example.myapplication.db.Message;

import java.util.List;

public class ChatSoloAdapter extends RecyclerView.Adapter<ChatSoloAdapter.Labelholder> {
    private Context context;
    List<Message> data;

    @NonNull
    @Override
    public ChatSoloAdapter.Labelholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.chat_solo_item, parent, false);
        return new Labelholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatSoloAdapter.Labelholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Labelholder extends RecyclerView.ViewHolder {
        public Labelholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
