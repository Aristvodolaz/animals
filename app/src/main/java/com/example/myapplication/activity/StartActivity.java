package com.example.myapplication.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.fragment.LostAnimalsFragment;
import com.example.myapplication.fragment.MapsFragment;
import com.example.myapplication.fragment.PerederzhkaFragment;
import com.example.myapplication.fragment.PersonalFragment;
import com.example.myapplication.fragment.WorkFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StartActivity extends AppCompatActivity {
    String login;
    ImageView personBtn, workBtn, perederzhkaBtn, animalsbtn, mapsBtn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity_layout);
        login = getIntent().getStringExtra("login");
        searchUsers(login);
        replaceFragment(MapsFragment.newInstance(), true);
        perederzhkaBtn = findViewById(R.id.perederzhka_btn);
        animalsbtn = findViewById(R.id.animals_btn);
        workBtn = findViewById(R.id.work_btn);
        personBtn = findViewById(R.id.person_btn);
//        mapsBtn = findViewById(R.id.maps);
        firebaseDatabase = FirebaseDatabase.getInstance();
        initViews();
    }

    private void initViews() {
//        mapsBtn.setOnClickListener(v->{
//            replaceFragment(MapsFragment.newInstance(), false);
//        });
        animalsbtn.setOnClickListener(v -> {
            replaceFragment(LostAnimalsFragment.newInstance(), false);
        });
        perederzhkaBtn.setOnClickListener(v -> {
            replaceFragment(PerederzhkaFragment.newInstance(), false);
        });
        workBtn.setOnClickListener(v -> {
            replaceFragment(WorkFragment.newInstance(), false);
        });
        personBtn.setOnClickListener(v -> {
            replaceFragment(PersonalFragment.newInstance(), false);
        });
    }

    private void searchUsers(String login) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = firebaseDatabase.getReference("Users");

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    if (userSnapshot.child("email").getValue(String.class).equals(login)) {
                        SharedPreferences preferences = getSharedPreferences("PERSONAL_INFO", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("name", userSnapshot.child("name").getValue(String.class));
                        editor.putString("surname", userSnapshot.child("surname").getValue(String.class));
                        editor.putString("phone", userSnapshot.child("phone").getValue(String.class));
                        editor.putString("age", userSnapshot.child("age").getValue(String.class));
                        editor.putString("city", userSnapshot.child("city").getValue(String.class));
                        editor.apply();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Обработка ошибки, если такая возникла
            }
        });
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }
}
