package com.example.myapplication.fragment;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.activity.CreateFormAnimalsActivity;
import com.example.myapplication.activity.StartActivity;
import com.example.myapplication.adapter.NeedPerederzhkaAdapter;
import com.example.myapplication.adapter.PerederzhkaAdapter;
import com.example.myapplication.adapter.PoteryashkiAnimalsAdapter;
import com.example.myapplication.db.Animals;
import com.example.myapplication.db.NeedPerderzhka;
import com.example.myapplication.db.Perederzhka;
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

public class PerederzhkaFragment extends Fragment {

    public static PerederzhkaFragment newInstance() {
        return new PerederzhkaFragment();
    }

    String[] lable = {"МЫ ПРЕДЛАГАЕМ ПЕРЕДЕРЖКУ","МЫ ИЩЕМ ПЕРЕДЕРЖКУ"};

    RecyclerView rv;
    TabLayout tl;
    ViewPager viewPager;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Perederzhka> data;
    List<NeedPerderzhka> data_need;
    ImageView addInfo, backArrow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.perederzhka_layout, container, false);
        rv = v.findViewById(R.id.recycler_view);
        tl = v.findViewById(R.id.tab_view);
        tl.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.color_for_reg));
        tl.setTabIndicatorFullWidth(true);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Data");
        addInfo = v.findViewById(R.id.add_info);
        addInfo.setOnClickListener(view->{
            Intent i = new Intent(getActivity(), CreateFormAnimalsActivity.class);
            i.putExtra("type_info", 2);
            startActivity(i);
        });

        backArrow = v.findViewById(R.id.back_arrow);

        backArrow.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), StartActivity.class);
            startActivity(intent);
        });
        initViewPager();
        getPerederzhka();

        return v;
    }

    private void initViewPager() {
        for (int i = 0; i < lable.length; i++) {
            tl.addTab(tl.newTab().setText(lable[i]));
        }

        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
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
    }


    private void getPerederzhka() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = db.collection("PerederzhkaAnimalsData");

        collectionRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Perederzhka> animalsList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getString("name");
                        String surname = document.getString("surname");
                        String phone = document.getString("phone");
                        String city = document.getString("city");
                        String type_animals = document.getString("type_animals");
                        String description = document.getString("description");
                        String imgURL = document.getString("imgURL");
                        String price = document.getString("price");
                        Perederzhka animal = new Perederzhka(name, surname, phone, city, type_animals, description, imgURL, price);
                        animalsList.add(animal);
                    }

//                    PoteryashkiAnimalsAdapter adapter = new PoteryashkiAnimalsAdapter(getContext(), animalsList);
                    PerederzhkaAdapter adapter = new PerederzhkaAdapter(getContext(), animalsList);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setAdapter(adapter);

                } else {
                    Log.d(TAG, "Ошибка при получении данных из Firestore: " + task.getException());
                }
            }
        });
    }

    private void getNeedPerederzhka() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = db.collection("PerederzhkaAnimalsNeedData");

        collectionRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<NeedPerderzhka> animalsList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getString("name");
                        String surname = document.getString("surname");
                        String phone = document.getString("phone");
                        String type_animals = document.getString("type_animals");
                        String poroda_animals = document.getString("poroda_animals");
                        String days = document.getString("days");
                        String city = document.getString("city");
                        NeedPerderzhka animal = new NeedPerderzhka(name, surname, phone, type_animals, poroda_animals, days, city);
                        animalsList.add(animal);
                    }

//                    PoteryashkiAnimalsAdapter adapter = new PoteryashkiAnimalsAdapter(getContext(), animalsList);
                    NeedPerederzhkaAdapter adapter = new NeedPerederzhkaAdapter(getContext(), animalsList);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setAdapter(adapter);

                } else {
                    Log.d(TAG, "Ошибка при получении данных из Firestore: " + task.getException());
                }
            }
        });
    }
}
