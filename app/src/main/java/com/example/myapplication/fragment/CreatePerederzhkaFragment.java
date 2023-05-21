package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.activity.CreateFormAnimalsActivity;
import com.example.myapplication.activity.StartActivity;
import com.example.myapplication.db.Animals;
import com.example.myapplication.db.NeedPerderzhka;
import com.example.myapplication.db.Perederzhka;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreatePerederzhkaFragment extends Fragment {
    TabLayout tl;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseFirestore db;
    ImageView addInfo;

    String name,surname, phone, address, animals, poroda, descriprion, imgUrl, price;
    int day;
    ImageView backArrow;
    String[] labels = {"МЫ ИЩЕМ ПЕРЕДЕРЖКУ","МЫ ПРЕДЛАГАЕМ ПЕРЕДЕРЖКУ"};
    //todo sddelat formu dlya perederzhki
    public static CreatePerederzhkaFragment newInstance() {
        return new CreatePerederzhkaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.create_perederzhka_layout, container, false);
        tl = v.findViewById(R.id.tab_view);
        backArrow = v.findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), StartActivity.class);
            startActivity(intent);
        });
        initViewPager();
        addPerederzhka();
        return v;
    }
    private void initViewPager() {
        for (int i = 0; i < labels.length; i++) {
            tl.addTab(tl.newTab().setText(labels[i]));
        }

        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        addPerederzhka();
                        break;
                    case 1:
                        addNeedPerederzhka();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void addToNeedDataBase(String name, String surname, String phone,
                                      String animals, String poroda, int days, String address) {

        CollectionReference dbDrivers = db.collection("PerederzhkaAnimalsNeedData");
        NeedPerderzhka needPerderzhka = new NeedPerderzhka(name, surname, phone,animals, poroda, days, address);
        dbDrivers.add(needPerderzhka).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getContext(), "Ваше объявление успешно добавлено!", Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Произошла ошибка, попробуйте позднее!", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void addToDataBase(String name, String surname, String phone,
                                   String animals, String opisanie, String imgUrl, String address, String price) {

        CollectionReference dbDrivers = db.collection("PerederzhkaAnimalsData");
        Perederzhka perederzhka = new Perederzhka(name, surname, phone, address, animals, opisanie, imgUrl, price);
        dbDrivers.add(perederzhka).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getContext(), "Ваше объявление успешно добавлено!", Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Произошла ошибка, попробуйте позднее!", Toast.LENGTH_LONG).show();
            }

        });
    }
    private void addNeedPerederzhka() {
        for (int i = 0; i < labels.length; i++) {
            tl.addTab(tl.newTab().setText(labels[i]));
        }
        addToNeedDataBase(name, surname, phone,animals,poroda, day,address);
    }

    private void addPerederzhka() {
        for (int i = 0; i < labels.length; i++) {
            tl.addTab(tl.newTab().setText(labels[i]));
        }
        addToDataBase(name, surname, phone,animals, descriprion,imgUrl,address, price);
    }
}
