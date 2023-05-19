package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.db.AnimalsLost;
import com.example.myapplication.db.NeedWorking;
import com.example.myapplication.db.Working;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateWorkerFragment extends Fragment {
    //todo sddelat formu dlya workerov

    TabLayout tl;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseFirestore db;

    String name,surname, phone, address, zadacha,description, min_price, max_price, age, city, price, imgUrl;
    String[] labels = {"МЫ ИЩЕМ РАБОТУ","МЫ НУЖДАЕМСЯ В УСЛУГЕ"};
    public static CreateWorkerFragment newInstance() {
        return new CreateWorkerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.create_work_layout, container, false);
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        addWork();
                        break;
                    case 1:
                        addNeedWorker();
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        addWork();
        return v;
    }
    private void addToNeedDataBase(String name, String surname, String phone, String adress, String zadacha,
                                        String description, String min_price, String max_price) {

        CollectionReference dbDrivers = db.collection("WorkingNeedData");
        NeedWorking needWorking = new NeedWorking(name ,surname,phone,adress,zadacha,description,min_price,max_price);
        dbDrivers.add(needWorking).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

    private void addToWorkDataBase(String name, String surname, String phone, String age, String city, String zadacha,
                                  String imgUrl, String price) {

        CollectionReference dbDrivers = db.collection("WorkingPersonData");
        Working working = new Working(name ,surname,phone,age, city,zadacha,imgUrl,price);
        dbDrivers.add(working).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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
    private void addNeedWorker() {
        for (int i = 0; i < labels.length; i++) {
            tl.addTab(tl.newTab().setText(labels[i]));
        }
        addToNeedDataBase(name, surname,phone,address,zadacha,description,min_price, max_price);

    }

    private void addWork() {
        for (int i = 0; i < labels.length; i++) {
            tl.addTab(tl.newTab().setText(labels[i]));
        }

        addToWorkDataBase(name,surname,phone,age,city,zadacha,imgUrl,price);
    }
}
