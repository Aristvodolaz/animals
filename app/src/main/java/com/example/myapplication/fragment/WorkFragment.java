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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.CreateFormAnimalsActivity;
import com.example.myapplication.activity.StartActivity;
import com.example.myapplication.adapter.NeedWorkAdapter;
import com.example.myapplication.adapter.PoteryashkiAnimalsAdapter;
import com.example.myapplication.adapter.WorkAdapter;
import com.example.myapplication.db.Animals;
import com.example.myapplication.db.NeedWorking;
import com.example.myapplication.db.Working;
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

import java.util.ArrayList;
import java.util.List;

public class WorkFragment extends Fragment {

    public static WorkFragment newInstance() {
        return new WorkFragment();
    }
    String[] lable = { "МЫ ПРЕДЛАГАЕМ УСЛУГУ","МЫ ИЩЕМ РАБОТНИКОВ"};
    RecyclerView rv;
    TabLayout tl;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Working> data;
    List<NeedWorking> data_need;
    ImageView addInfo, backArrow;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.work_layout, container, false);
        rv = v.findViewById(R.id.recycler_view);
        tl = v.findViewById(R.id.tab_view);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Data");
        addInfo = v.findViewById(R.id.add_info);
        addInfo.setOnClickListener(view->{
            Intent i = new Intent(getActivity(), CreateFormAnimalsActivity.class);
            i.putExtra("type_info", 1);
            startActivity(i);
        });
        backArrow = v.findViewById(R.id.back_arrow);

        backArrow.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), StartActivity.class);
            startActivity(intent);
        });
        initViews();
        getWorking();
        return v;

    }

    private void initViews() {
        for (int i = 0; i < lable.length; i++) {
            tl.addTab(tl.newTab().setText(lable[i]));
        }
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        getWorking();
                        break;
                    case 1:
                        getNeedWorking();
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

    private void getNeedWorking() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = db.collection("WorkingNeedData");

        collectionRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<NeedWorking> data_need = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getString("name");
                        String surname = document.getString("surname");
                        String anim = document.getString("animals");
                        String poroda = document.getString("poroda");
                        String days = document.getString("days");
                        String phone = document.getString("phone");
                        String address = document.getString("address");
                        NeedWorking animal = new NeedWorking(name, surname,phone,address,anim,poroda,days);
                        data_need.add(animal);
                    }

//                    PoteryashkiAnimalsAdapter adapter = new PoteryashkiAnimalsAdapter(getContext(), animalsList);
                    NeedWorkAdapter adapter = new NeedWorkAdapter(getContext(), data_need);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setAdapter(adapter);

                } else {
                    Log.d(TAG, "Ошибка при получении данных из Firestore: " + task.getException());
                }
            }
        });
    }

    private void getWorking() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = db.collection("WorkingPersonData");

        collectionRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Working> data = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getString("name");
                        String surname = document.getString("surname");
                        String age = document.getString("age");
                        String city = document.getString("city");
                        String work = document.getString("work");
                        String phone = document.getString("phone");
                        String price = document.getString("price");
                        String img = document.getString("imgURL");
                        Working animal = new Working(name, surname, phone, age, city, work,img, price);
                        data.add(animal);
                    }

//                    PoteryashkiAnimalsAdapter adapter = new PoteryashkiAnimalsAdapter(getContext(), animalsList);
                    WorkAdapter adapter = new WorkAdapter(getContext(), data);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setAdapter(adapter);

                } else {
                    Log.d(TAG, "Ошибка при получении данных из Firestore: " + task.getException());
                }
            }
        });
    }
}
