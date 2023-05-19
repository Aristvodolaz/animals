package com.example.myapplication.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;


public class PersonalFragment extends Fragment {

    public static PersonalFragment newInstance() {
        return new PersonalFragment();
    }
    String name, surname, age, city, pol, phone, img;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.personal_layout, container, false);
        initViews();
        return v;
    }

    private void initViews() {
        getInfoForPerson();
    }

    private void getInfoForPerson(){
        SharedPreferences sp = getActivity().getSharedPreferences("PERSONAL_INFO", Context.MODE_PRIVATE);
        name =  sp.getString("name", "");
        surname = sp.getString("surname", "");
        phone = sp.getString("phone", "");
        age = sp.getString("age", "");
        city = sp.getString("city", "");
        img =sp.getString("img", "");
    }
}
