package com.example.myapplication.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.db.Users;
import com.example.myapplication.fragment.LostAnimalsFragment;
import com.example.myapplication.fragment.MapsFragment;
import com.example.myapplication.fragment.PerederzhkaFragment;
import com.example.myapplication.fragment.PersonalFragment;
import com.example.myapplication.fragment.WorkFragment;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    String login;
    List<Users> data;
    ImageView personBtn, workBtn, perederzhkaBtn, animalsbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity_layout);
        login = getIntent().getStringExtra("login");
        searchUsers(login);
        //todo perehod na fragment posle auth
        replaceFragment(MapsFragment.newInstance(), true);
        perederzhkaBtn = findViewById(R.id.perederzhka_btn);
        animalsbtn = findViewById(R.id.animals_btn);
        workBtn =findViewById(R.id.work_btn);
        personBtn = findViewById(R.id.person_btn);

        initViews();
    }

    private void initViews() {
        personBtn.setOnClickListener(v->{
            replaceFragment(LostAnimalsFragment.newInstance(), true);
        });
        perederzhkaBtn.setOnClickListener(v->{
            replaceFragment(PerederzhkaFragment.newInstance(), true);
        });
        workBtn.setOnClickListener(v->{
            replaceFragment(WorkFragment.newInstance(), true);
        });
        personBtn.setOnClickListener(v->{
            replaceFragment(PersonalFragment.newInstance(), true);
        });
    }

    //todo sdelat polycheniy dannh polzovatelya po loginu b zpisat s SharedPrefernces(nazvanie lezhat v Personal fragment)
    private void searchUsers(String login ) {

        data = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getPhone().equals(login)) {
                    SharedPreferences preferences = getSharedPreferences("PERSONAL_INFO", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("name", data.get(i).getName());
                    editor.putString("surname", data.get(i).getSurname());
                    editor.putString("phone", data.get(i).getPhone());
                    editor.putString("age", data.get(i).getAge());
                    editor.putString("city", data.get(i).getCity());
                    editor.putString("img", data.get(i).getImgURL());
                }
            }


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
