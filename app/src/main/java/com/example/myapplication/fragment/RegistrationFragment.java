package com.example.myapplication.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class RegistrationFragment extends Fragment {

    public static final String TYPE_INFO = "type_info";

    public static RegistrationFragment newInstance(int type) {

        //todo registraciya po telefonu

        Bundle args = new Bundle();
        args.putInt(TYPE_INFO, type);

        RegistrationFragment fragment = new RegistrationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.registration_layout, container, false);
        initViews();
        return  v;
    }

    private void initViews() {
        if(getArguments().getInt(TYPE_INFO) == 0){
            //todo for simple users
        } else if (getArguments().getInt(TYPE_INFO) == 1) {
            //todo for doggiesitter
        } else if(getArguments().getInt(TYPE_INFO)==2){
            //todo for perederzhka
        }
    }

}

