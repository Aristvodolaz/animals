package com.example.myapplication.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.KindHandsAdapter;
import com.example.myapplication.adapter.LostAnimals;
import com.example.myapplication.adapter.PoteryashkiAnimalsAdapter;
import com.example.myapplication.db.Animals;
import com.example.myapplication.db.AnimalsLost;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.checker.units.qual.C;

import java.util.List;

public class LostAnimalsFragment extends Fragment {


    public static LostAnimalsFragment newInstance() {

        return new LostAnimalsFragment();
    }
    RecyclerView rv;
    TabLayout tl;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<AnimalsLost> data;
    List<Animals> data_without_home;

    String[] labels = {"ИЩЕМ ДОМ", "ПОТЕРЯШКИ", "ДОБРЫЕ РУКИ"};

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.lost_animals_layout, container, false);
        rv = v.findViewById(R.id.recycler_view);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Data");
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        getInfoLostAnimals();
                        break;
                    case 1:
                        getPoteryazhki();
                        break;
                    case 2:
                        getKingHands();
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
        getInfoLostAnimals();
        return v;
    }
    //todo dobtye ruki
    public void getKingHands(){
        for (int i = 0 ; i < labels.length ; i++){
            tl.addTab(tl.newTab().setText(labels[i]));
        }
        databaseReference = firebaseDatabase.getReference("KindAnimalsData");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                AnimalsLost animalsLost = snapshot.getValue(AnimalsLost.class);
                data.add(animalsLost);
                if(data.isEmpty() || data == null){
                    Toast.makeText(getContext(), "Список животных пуст!", Toast.LENGTH_LONG).show();
                } else  {
                    KindHandsAdapter adapter = new KindHandsAdapter(getContext(), data);
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

    //todo bezdomnye animals
    private void getPoteryazhki(){
        for (int i = 0 ; i < labels.length ; i++){
            tl.addTab(tl.newTab().setText(labels[i]));
        }
        databaseReference = firebaseDatabase.getReference("PoteryashkiAnimalsData");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Animals animalsData = snapshot.getValue(Animals.class);
                data_without_home.add(animalsData);
                if(data.isEmpty() || data == null){
                    Toast.makeText(getContext(), "Список бездомных животных пуст", Toast.LENGTH_LONG).show();
                } else  {
                    PoteryashkiAnimalsAdapter adapter = new PoteryashkiAnimalsAdapter(getContext(), data_without_home);
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

    //todo poteryvshiesya domashnie animals
    private void getInfoLostAnimals() {
        databaseReference = firebaseDatabase.getReference("addAnimalsData");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                AnimalsLost animalsData = snapshot.getValue(AnimalsLost.class);
                data.add(animalsData);
                if(data.isEmpty() || data == null){
                    Toast.makeText(getContext(), "Список пропавших животных пуст", Toast.LENGTH_LONG).show();
                } else  {
                    LostAnimals adapter = new LostAnimals(getContext(), data);
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
}
