package com.example.myapplication.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.db.Users;
import com.example.myapplication.fragment.MapsFragment;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    String login;
    List<Users> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity_layout);
        login = getIntent().getStringExtra("login");
        searchUsers(login);
        //todo perehod na fragment posle auth
        replaceFragment(MapsFragment.newInstance(), true);
    }

    //todo sdelat polycheniy dannh polzovatelya po loginu b zpisat s SharedPrefernces(nazvanie lezhat v Personal fragment)
    private void searchUsers(String login ) {

        data = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getPhone().equals(login)) {
                    // SharedPreferences sp =;
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
