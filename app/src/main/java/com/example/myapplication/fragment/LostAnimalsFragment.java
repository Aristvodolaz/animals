package com.example.myapplication.fragment;


import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.CreateFormAnimalsActivity;
import com.example.myapplication.activity.StartActivity;
import com.example.myapplication.adapter.KindHandsAdapter;
import com.example.myapplication.adapter.LostAnimals;
import com.example.myapplication.adapter.PoteryashkiAnimalsAdapter;
import com.example.myapplication.db.Animals;
import com.example.myapplication.db.AnimalsLost;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
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
    List<Animals> animalsList;
    ImageView addInfo;

    String[] labels = {"ИЩЕМ ДОМ", "ПОТЕРЯШКИ", "ДОБРЫЕ РУКИ"};

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.lost_animals_layout, container, false);
        rv = v.findViewById(R.id.recycler_view);
        tl = v.findViewById(R.id.tab_view);
        addInfo = v.findViewById(R.id.add_info);
        addInfo.setOnClickListener(view->{
            Intent i = new Intent(getActivity(), CreateFormAnimalsActivity.class);
            i.putExtra("type_info", 0);
            startActivity(i);
        });
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
//        databaseReference = firebaseDatabase.getReference("Data");

        initViews();
        getInfoLostAnimals();
        return v;
    }

    public void initViews(){
        for (int i = 0 ; i < labels.length ; i++){
            tl.addTab(tl.newTab().setText(labels[i]));
        }
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
    }
    //todo dobtye ruki
    public void getKingHands(){

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

    //todo analogiya animals
    private void getPoteryazhki(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = db.collection("addAnimalsData");

        collectionRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Animals> animalsList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getString("adress");
                        String desc = document.getString("description");
                        String pol = document.getString("pol");
                        String img = document.getString("imgURL");
                        Animals animal = new Animals(name, img, pol, desc);
                        animalsList.add(animal);
                    }

//                    PoteryashkiAnimalsAdapter adapter = new PoteryashkiAnimalsAdapter(getContext(), animalsList);
                    PoteryashkiAnimalsAdapter adapter = new PoteryashkiAnimalsAdapter(getContext(), animalsList);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setAdapter(adapter);

                } else {
                    Log.d(TAG, "Ошибка при получении данных из Firestore: " + task.getException());
                }
            }
    });
    }

    //todo poteryvshiesya domashnie animals
    private void getInfoLostAnimals() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = db.collection("PoteryashkiAnimalsData");

        collectionRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Animals> animalsList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getString("adress");
                        String desc = document.getString("description");
                        String pol = document.getString("pol");
                        String img = document.getString("imgURL");
                        Animals animal = new Animals(name, img, pol, desc);
                        animalsList.add(animal);
                    }

//                    PoteryashkiAnimalsAdapter adapter = new PoteryashkiAnimalsAdapter(getContext(), animalsList);
//                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
//                    rv.setAdapter(adapter);

                } else {
                    Log.d(TAG, "Ошибка при получении данных из Firestore: " + task.getException());
                }
            }
        });
    }
}
