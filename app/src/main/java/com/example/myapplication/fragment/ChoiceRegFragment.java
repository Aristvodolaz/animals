package com.example.myapplication.fragment;


import android.annotation.SuppressLint;
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

public class ChoiceRegFragment extends Fragment {
    Button workBtn, userBtn, peredBtn, priutBtn, klinnikaBtn;
    public static ChoiceRegFragment newInstance() {
        return new ChoiceRegFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.choise_reg_layout, container, false);
        workBtn = v.findViewById(R.id.work_btn);
        userBtn = v.findViewById(R.id.user_btn);
        peredBtn = v.findViewById(R.id.pered_btn);
        priutBtn =v.findViewById(R.id.priut_btn);
        klinnikaBtn = v.findViewById(R.id.klinnika_btn);

        userBtn.setOnClickListener(view -> {
            ((MainActivity) getActivity()).replaceFragment(RegistrationFragment.newInstance(1), false);
        });
        workBtn.setOnClickListener(view -> {
            ((MainActivity) getActivity()).replaceFragment(RegistrationFragment.newInstance(1), false);
        });
        peredBtn.setOnClickListener(view -> {
            ((MainActivity) getActivity()).replaceFragment(RegistrationFragment.newInstance(2), false);
        });

//        todo in development
//        priutBtn.setOnClickListener(view -> {
//            ((MainActivity) getActivity()).replaceFragment(RegistrationFragment.newInstance(4), false);
//        });
//        klinnikaBtn.setOnClickListener(view -> {
//            ((MainActivity) getActivity()).replaceFragment(RegistrationFragment.newInstance(5), false);
//        });

        return v;
    }
}
