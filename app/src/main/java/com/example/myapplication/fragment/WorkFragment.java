package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.CreateFormAnimalsActivity;
import com.example.myapplication.adapter.NeedWorkAdapter;
import com.example.myapplication.adapter.WorkAdapter;
import com.example.myapplication.db.NeedWorking;
import com.example.myapplication.db.Working;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class WorkFragment extends Fragment {

    public static WorkFragment newInstance() {
        return new WorkFragment();
    }
    String[] lable = {"МЫ ИЩЕМ РАБОТНИКОВ", "МЫ ПРЕДЛАГАЕМ УСЛУГУ"};
    RecyclerView rv;
    TabLayout tl;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Working> data;
    List<NeedWorking> data_need;
    ImageView addInfo;

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

        databaseReference = firebaseDatabase.getReference("WorkingNeedData");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NeedWorking dataWorking = snapshot.getValue(NeedWorking.class);
                data_need.add(dataWorking);
                if (data_need.isEmpty() || data_need == null) {
                    Toast.makeText(getContext(), "Список предложений пуст", Toast.LENGTH_LONG).show();
                } else {
                    NeedWorkAdapter adapter = new NeedWorkAdapter(getContext(), data_need);
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

    private void getWorking() {
        databaseReference = firebaseDatabase.getReference("WorkingPersonData");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Working dataWorking = snapshot.getValue(Working.class);
                data.add(dataWorking);
                if (data.isEmpty() || data == null) {
                    Toast.makeText(getContext(), "Список предложений пуст", Toast.LENGTH_LONG).show();
                } else {
                    WorkAdapter adapter = new WorkAdapter(getContext(), data);
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
