package com.example.myapplication.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;


public class ChatSoloMessageFragment extends Fragment {
    public static ChatSoloMessageFragment newInstance(Long id) {
        Bundle args = new Bundle();
        args.putLong("id_chats", id);
        ChatSoloMessageFragment fragment = new ChatSoloMessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.chat_solo_layout, container, false);
        initViews();
        return v;
    }

    private void initViews() {

    }
}
