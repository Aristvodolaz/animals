package com.example.myapplication.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.activity.StartActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;


public class PersonalFragment extends Fragment {

    public static PersonalFragment newInstance() {
        return new PersonalFragment();
    }
    String name, surname, age, city, pol, phone, img;
    ImageView backArrow, exit;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;

    private TextView nameFr, surnameFr, ageFr, cityFr, phoneFr, imgFr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.personal_layout, container, false);

        backArrow = v.findViewById(R.id.back_arrow);

        backArrow.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), StartActivity.class);
            startActivity(intent);
        });

        exit = v.findViewById(R.id.exit);
        exit.setOnClickListener(view -> {
            mAuth.signOut();
        });

        nameFr = v.findViewById(R.id.name);
        surnameFr = v.findViewById(R.id.surname);
        ageFr = v.findViewById(R.id.age);
        phoneFr = v.findViewById(R.id.phone);
        cityFr = v.findViewById(R.id.city);

        initViews();
        return v;
    }

    private void initViews() {
        getInfoForPerson();
    }

    private void getInfoForPerson(){

        SharedPreferences sp = getActivity().getSharedPreferences("PERSONAL_INFO", Context.MODE_PRIVATE);

        nameFr.setText(sp.getString("name", nameFr.toString()));
        surnameFr.setText(sp.getString("surname", surnameFr.toString()));
        phoneFr.setText(sp.getString("phone", phoneFr.toString()));
        ageFr.setText(sp.getString("age", ageFr.toString()));
        cityFr.setText(sp.getString("city", cityFr.toString()));
        img =sp.getString("img", "");
    }

}
