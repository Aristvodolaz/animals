package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.NeedPerederzhkaAdapter;
import com.example.myapplication.adapter.PerederzhkaAdapter;
import com.example.myapplication.adapter.PoteryashkiAnimalsAdapter;
import com.example.myapplication.adapter.WorkAdapter;
import com.example.myapplication.db.Animals;
import com.example.myapplication.db.AnimalsLost;
import com.example.myapplication.db.NeedPerderzhka;
import com.example.myapplication.db.NeedWorking;
import com.example.myapplication.db.Perederzhka;
import com.example.myapplication.db.Working;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class PerederzhkaFragment extends Fragment {

    public static PerederzhkaFragment newInstance() {
        return new PerederzhkaFragment();
    }

    String[] lable = {"МЫ ИЩЕМ ПЕРЕДЕРЖКУ", "МЫ ПРЕДЛАГАЕМ ПЕРЕДЕРЖКУ"};

    RecyclerView rv;
    TabLayout tl;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Perederzhka> data;
    List<NeedPerderzhka> data_need;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.perederzhka_layout, container, false);
        rv = v.findViewById(R.id.recycler_view);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Data");
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        getPerederzhka();
                        break;
                    case 1:
                        getNeedPerederzhka();
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
        getPerederzhka();
        return v;

    }

    private void getNeedPerederzhka() {
        for (int i = 0 ; i < lable.length ; i++){
            tl.addTab(tl.newTab().setText(lable[i]));
        }
        databaseReference = firebaseDatabase.getReference("PerederzhkaAnimalsNeedData");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NeedPerderzhka needPerderzhka = snapshot.getValue(NeedPerderzhka.class);
                data_need.add(needPerderzhka);
                if(data_need.isEmpty() || data_need == null){
                    Toast.makeText(getContext(), "Список предложений пуст", Toast.LENGTH_LONG).show();
                } else  {
                    NeedPerederzhkaAdapter adapter = new NeedPerederzhkaAdapter(getContext(), data_need);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getPerederzhka() {
        for (int i = 0 ; i < lable.length ; i++){
            tl.addTab(tl.newTab().setText(lable[i]));
        }
        databaseReference = firebaseDatabase.getReference("PerederzhkaAnimalsData");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Perederzhka perederzhka = snapshot.getValue(Perederzhka.class);
                data.add(perederzhka);
                if(data.isEmpty() || data == null){
                    Toast.makeText(getContext(), "Список предложений пуст", Toast.LENGTH_LONG).show();
                } else  {
                    PerederzhkaAdapter adapter = new PerederzhkaAdapter(getContext(), data);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    //todo sdelat po analogii s LostAnimalsFragment + dodelat po analogii adapter + sverstat .xml
}
