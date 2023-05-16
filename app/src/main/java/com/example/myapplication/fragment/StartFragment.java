package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;

public class StartFragment extends Fragment {

    Button initBtn, regBtn;

    public static StartFragment newInstance() {
        return new StartFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.start_layout, container, false);
        initBtn = v.findViewById(R.id.init_btn);
        regBtn = v.findViewById(R.id.reg_btn);
        initViews();
        return v;
    }

    public void initViews() {
        initBtn.setOnClickListener(v->{
            ((MainActivity)getActivity()).replaceFragment(AuthFragment.newInstance(), true);
        });
        regBtn.setOnClickListener(v->{
            ((MainActivity)getActivity()).replaceFragment(ChoiceRegFragment.newInstance(), true);
        });
    }
}
