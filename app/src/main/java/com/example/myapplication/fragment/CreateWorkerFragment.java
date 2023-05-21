package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.activity.CreateFormAnimalsActivity;
import com.example.myapplication.activity.StartActivity;
import com.example.myapplication.db.AnimalsLost;
import com.example.myapplication.db.NeedWorking;
import com.example.myapplication.db.Working;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateWorkerFragment extends Fragment {
    //todo sddelat formu dlya workerov

    LinearLayout adressLine, daysLine, porodaLine, animLine,
            workLine,priceLine, cityLine, ageLine;
    TabLayout tl;
    Button addPhoto, addZapis;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseFirestore db;
//    ImageView addInfo;

    String name,surname, phone, address,anim, age, city, price, imgUrl, poroda, days, work;
    EditText nameET, surnameET, phoneET ,addressET, animalET, ageET, cityET, priceET , porodaET, daysET, workET;
    ImageView backArrow;
    String[] labels = {"МЫ ИЩЕМ РАБОТУ","МЫ НУЖДАЕМСЯ В УСЛУГЕ"};
    public static CreateWorkerFragment newInstance() {
        return new CreateWorkerFragment();
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.create_work_layout, container, false);
        tl = v.findViewById(R.id.tab_view);
        backArrow = v.findViewById(R.id.back_arrow);
        addressET = v.findViewById(R.id.user_addres);
        nameET = v.findViewById(R.id.name_uesr);
        surnameET = v.findViewById(R.id.surname_uesr);
        phoneET = v.findViewById(R.id.phone_user);
        animalET = v.findViewById(R.id.animals_type);
        porodaET =v.findViewById(R.id.poroda_anim);
        daysET = v.findViewById(R.id.num_day);
        addZapis = v.findViewById(R.id.add_zapis);
        addPhoto = v.findViewById(R.id.add_photo);

        ageET = v.findViewById(R.id.age);
        cityET = v.findViewById(R.id.city);
        priceET = v.findViewById(R.id.price);
        workET = v.findViewById(R.id.work);

        adressLine = v.findViewById(R.id.adress_line);
        animLine = v.findViewById(R.id.anim_line);
        priceLine = v.findViewById(R.id.price_line);
        porodaLine = v.findViewById(R.id.poroda_line);
        cityLine = v.findViewById(R.id.city_line);
        ageLine = v.findViewById(R.id.age_line);
        daysLine = v.findViewById(R.id.days_line);
        workLine = v.findViewById(R.id.work_line);

        backArrow.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), StartActivity.class);
            startActivity(intent);
        });
        initViews();
        addWork();
        return v;
    }

    private void initViews() {
        for (int i = 0; i < labels.length; i++) {
            tl.addTab(tl.newTab().setText(labels[i]));
        }
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        addWork();
                        break;
                    case 1:
                        addNeedWorker();
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void addToNeedDataBase(String name, String surname, String phone, String adress, String anim,
                                        String poroda, String days) {
        db = FirebaseFirestore.getInstance(); // Раскомментируйте эту строку для инициализации объекта db
        CollectionReference dbDrivers = db.collection("WorkingNeedData");
        NeedWorking needWorking = new NeedWorking(name, surname,phone,adress,anim,poroda,days);
        dbDrivers.add(needWorking).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getContext(), "Ваше объявление успешно добавлено!", Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Произошла ошибка, попробуйте позднее!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addToWorkDataBase(String name, String surname, String phone, String age, String city, String zadacha,
                                  String imgUrl, String price) {
        db = FirebaseFirestore.getInstance();
        CollectionReference dbDrivers = db.collection("WorkingPersonData");
        Working working = new Working(name ,surname,phone,age, city,zadacha,imgUrl,price);
        dbDrivers.add(working).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getContext(), "Ваше объявление успешно добавлено!", Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Произошла ошибка, попробуйте позднее!", Toast.LENGTH_LONG).show();
            }

        });
    }
    private void addNeedWorker() {
        addPhoto.setVisibility(View.GONE);
        workLine.setVisibility(View.GONE);
        priceLine.setVisibility(View.GONE);
        cityLine.setVisibility(View.GONE);
        ageLine.setVisibility(View.GONE);

        adressLine.setVisibility(View.VISIBLE);
        daysLine.setVisibility(View.VISIBLE);
        porodaLine.setVisibility(View.VISIBLE);
        animLine.setVisibility(View.VISIBLE);

        addZapis.setOnClickListener(v-> {
            if (!addressET.getText().toString().equals("") || addressET.getText() != null) {
                address = addressET.getText().toString();
            } else {
                addressET.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if (!porodaET.getText().toString().equals("") || porodaET.getText() != null) {
                poroda = porodaET.getText().toString();
            } else {
                porodaET.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if (!nameET.getText().toString().equals("") || nameET.getText() != null) {
                name = nameET.getText().toString();
            } else {
                nameET.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if (!surnameET.getText().toString().equals("") || surnameET.getText() != null) {
                surname = surnameET.getText().toString();
            } else {
                surnameET.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if (!daysET.getText().toString().equals("") || daysET.getText() != null) {
                days = daysET.getText().toString();
            } else {
                daysET.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if (!phoneET.getText().toString().equals("") || phoneET.getText() != null) {
                phone = phoneET.getText().toString();
            } else {
                phoneET.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if (!animalET.getText().toString().equals("") || animalET.getText() != null) {
                anim = animalET.getText().toString();
            } else {
                animalET.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            imgUrl = "a";
            if(!nameET.getText().toString().equals("") && !surnameET.getText().toString().equals("")&&!phoneET.getText().toString().equals("")
                    && !daysET.getText().toString().equals("") && !animalET.getText().toString().equals("") && !addressET.getText().toString().equals("")
                    && !porodaET.getText().toString().equals(""))
                addToNeedDataBase(name,surname, phone,address,anim,poroda,days);
//                addToWorkDataBase(name, surname, phone, age, city, zadacha, imgUrl, price);
        });
    }

    private void addWork() {
        addPhoto.setVisibility(View.VISIBLE);
        adressLine.setVisibility(View.GONE);
        daysLine.setVisibility(View.GONE);
        porodaLine.setVisibility(View.GONE);
        animLine.setVisibility(View.GONE);

        workLine.setVisibility(View.VISIBLE);
        priceLine.setVisibility(View.VISIBLE);
        cityLine.setVisibility(View.VISIBLE);
        ageLine.setVisibility(View.VISIBLE);

        addZapis.setOnClickListener(v-> {
            if (!nameET.getText().toString().equals("") || nameET.getText() != null) {
                name = nameET.getText().toString();
            } else {
                nameET.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if (!surnameET.getText().toString().equals("") || surnameET.getText() != null) {
                surname = surnameET.getText().toString();
            } else {
                surnameET.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if (!phoneET.getText().toString().equals("") || phoneET.getText() != null) {
                phone = phoneET.getText().toString();
            } else {
                phoneET.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if (!workET.getText().toString().equals("") || workET.getText() != null) {
                work = workET.getText().toString();
            } else {
                workET.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if (!cityET.getText().toString().equals("") || cityET.getText() != null) {
                city = cityET.getText().toString();
            } else {
                cityET.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if (!ageET.getText().toString().equals("") || ageET.getText() != null) {
                age = ageET.getText().toString();
            } else {
                ageET.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if (!priceET.getText().toString().equals("") || priceET.getText() != null) {
                price = priceET.getText().toString();
            } else {
                priceET.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }

            imgUrl = "a";
            if(!nameET.getText().toString().equals("") && !surnameET.getText().toString().equals("")&&!phoneET.getText().toString().equals("")
                    && !cityET.getText().toString().equals("") && !ageET.getText().toString().equals("") && !priceET.getText().toString().equals("")
                    && !workET.getText().toString().equals(""))

                addToWorkDataBase(name, surname, phone, age, city, work, imgUrl, price);
        });
    }
}
