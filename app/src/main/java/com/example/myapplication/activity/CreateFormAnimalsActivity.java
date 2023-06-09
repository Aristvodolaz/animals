package com.example.myapplication.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.fragment.CreateAnimalsFragment;
import com.example.myapplication.fragment.CreatePerederzhkaFragment;
import com.example.myapplication.fragment.CreateWorkerFragment;

public class CreateFormAnimalsActivity extends AppCompatActivity {

    FrameLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);

        Bundle b = getIntent().getExtras();
        if(b.getInt("type_info") == 0){
            //todo create form for animals
            replaceFragment(CreateAnimalsFragment.newInstance(), false);
        } else if (b.getInt("type_info") ==1) {
            //todo create form for worker
            replaceFragment(CreateWorkerFragment.newInstance(), false);
        } else if (b.getInt("type_info") == 2) {
            //todo create form for perederzhka
            replaceFragment(CreatePerederzhkaFragment.newInstance(), false);
        }
//        replaceFragment(StartFragment.newInstance(), true);
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
