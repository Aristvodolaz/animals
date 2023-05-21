package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.db.Animals;
import com.example.myapplication.db.AnimalsLost;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateAnimalsFragment extends Fragment {

    LinearLayout poroda, klichka, date, user;
    TextView nameAddress;
    Button addZapis, addPhoto;

    String[] label = {"МЫ ИЩЕМ ДОМ", "ПОТЕРЯШКИ", "ДОБРЫЕ РУКИ" };
    TabLayout tl;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText address, porodaAnim, datePropazhi, pol, opisanie, nameAnim, nameUser, phoneUser;
    TextView mesto;
    String adress_potery, poroda_anim, date_prodazhi, pol_anim, imgUrl, opisanie_anim, name_anim, name_user, phone_user;
    private FirebaseFirestore db;
    //todo sddelat formu dlya zhivotnyx
    public static CreateAnimalsFragment newInstance() {
        return new CreateAnimalsFragment();
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.create_lost_animals_layout, container, false);
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("Data");

        // Initialize FirebaseFirestore
        db = FirebaseFirestore.getInstance();
        tl = v.findViewById(R.id.tab_view);
        poroda = v.findViewById(R.id.poroda);
        klichka = v.findViewById(R.id.klichka_n);
        date = v.findViewById(R.id.date);
        user = v.findViewById(R.id.user);
        mesto = v.findViewById(R.id.mesto);

        address = v.findViewById(R.id.adress_anim);
        porodaAnim = v.findViewById(R.id.poroda_anim);
        datePropazhi = v.findViewById(R.id.date_propazhi);
        pol = v.findViewById(R.id.pol_anim);
        opisanie = v.findViewById(R.id.descript_anim);
        nameAnim = v.findViewById(R.id.name_anim);
        nameUser = v.findViewById(R.id.user_name);
        phoneUser = v.findViewById(R.id.user_phone);

        addZapis = v.findViewById(R.id.add_zapis);
        addPhoto = v.findViewById(R.id.add_photo);

        initViewPager();
        addAnimals();
        return v;
    }
    private void initViewPager() {
        for (int i = 0; i < label.length; i++) {
            tl.addTab(tl.newTab().setText(label[i]));
        }

        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        addAnimals();
                        break;
                    case 1:
                        addPoteryashki();
                        break;
                    case 2:
                        addKindHands();
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
    private void addToKindHandsDataBase(String adress_potery, String poroda_anim, String date_prodazhi, String pol_anim, String imgUrl,
                                        String opisanie_anim, String name_anim, String name_user, String phone_user) {

        CollectionReference dbDrivers = db.collection("KindAnimalsData");
        AnimalsLost animalsLost = new AnimalsLost(adress_potery ,poroda_anim,date_prodazhi,imgUrl,pol_anim,opisanie_anim,name_anim,name_user,phone_user);
        dbDrivers.add(animalsLost).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

    private void addToPoteryashkaDataBase(String adress_potery, String poroda_anim, String date_prodazhi, String pol_anim, String imgUrl,
                                        String opisanie_anim, String name_anim, String name_user, String phone_user) {

        CollectionReference dbDrivers = db.collection("PoteryashkiAnimalsData");
        AnimalsLost animalsLost = new AnimalsLost(adress_potery ,poroda_anim,date_prodazhi,imgUrl,pol_anim,opisanie_anim,name_anim,name_user,phone_user);
        dbDrivers.add(animalsLost).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

    private void addToAnimalsDataBase(String adress_potery, String pol_anim, String imgUrl, String opisanie_anim) {
        CollectionReference dbDrivers = db.collection("addAnimalsData");
        Animals animals = new Animals(adress_potery, imgUrl, pol_anim, opisanie_anim);
        dbDrivers.add(animals)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getContext(), "Ваше объявление успешно добавлено!", Toast.LENGTH_LONG).show();
                        getActivity().finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Произошла ошибка, попробуйте позднее!", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void addKindHands() {

        poroda.setVisibility(View.VISIBLE);
        klichka.setVisibility(View.VISIBLE);
        date.setVisibility(View.VISIBLE);
        user.setVisibility(View.VISIBLE);
        addZapis.setOnClickListener(v->{
        if (!address.getText().toString().equals("") || address.getText() != null) {
            adress_potery = address.getText().toString();
        } else {
            address.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!porodaAnim.getText().toString().equals("") || porodaAnim.getText() != null) {
            poroda_anim = porodaAnim.getText().toString();
        } else {
            porodaAnim.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!datePropazhi.getText().toString().equals("") || datePropazhi.getText() != null) {
            date_prodazhi = datePropazhi.getText().toString();
        } else {
            datePropazhi.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!opisanie.getText().toString().equals("") || opisanie.getText() != null) {
            opisanie_anim = opisanie.getText().toString();
        } else {
            opisanie.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!pol.getText().toString().equals("") || pol.getText() != null) {
            pol_anim = pol.getText().toString();
        } else {
            pol.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!nameAnim.getText().toString().equals("") || nameAnim.getText() != null) {
            name_anim = nameAnim.getText().toString();
        } else {
            nameAnim.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!nameUser.getText().toString().equals("") || nameUser.getText() != null) {
            name_user = nameUser.getText().toString();
        } else {
            nameUser.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!phoneUser.getText().toString().equals("") || phoneUser.getText() != null) {
            phone_user = phoneUser.getText().toString();
        } else {
            phoneUser.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        imgUrl = "";

            if(!address.getText().toString().equals("") && !porodaAnim.getText().toString().equals("")&&!datePropazhi.getText().toString().equals("")
                    && !opisanie.getText().toString().equals("") && !pol.getText().toString().equals("") && !nameAnim.getText().toString().equals("")
                    && !nameUser.getText().toString().equals("") && !phoneUser.getText().toString().equals("") )
                addToKindHandsDataBase(adress_potery, poroda_anim, date_prodazhi, pol_anim, imgUrl, opisanie_anim, name_anim, name_user, phone_user);

        });

    }

    private void addPoteryashki() {

        mesto.setText("Место пропажи");
        poroda.setVisibility(View.VISIBLE);
        klichka.setVisibility(View.VISIBLE);
        date.setVisibility(View.VISIBLE);
        user.setVisibility(View.VISIBLE);
        addZapis.setOnClickListener(v -> {
        if (!address.getText().toString().equals("") || address.getText() != null) {
            adress_potery = address.getText().toString();
        } else {
            address.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!porodaAnim.getText().toString().equals("") || porodaAnim.getText() != null) {
            poroda_anim = porodaAnim.getText().toString();
        } else {
            porodaAnim.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!datePropazhi.getText().toString().equals("") || datePropazhi.getText() != null) {
            date_prodazhi = datePropazhi.getText().toString();
        } else {
            datePropazhi.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!opisanie.getText().toString().equals("") || opisanie.getText() != null) {
            opisanie_anim = opisanie.getText().toString();
        } else {
            opisanie.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!pol.getText().toString().equals("") || pol.getText() != null) {
            pol_anim = pol.getText().toString();
        } else {
            pol.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!nameAnim.getText().toString().equals("") || nameAnim.getText() != null) {
            name_anim = nameAnim.getText().toString();
        } else {
            nameAnim.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!nameUser.getText().toString().equals("") || nameUser.getText() != null) {
            name_user = nameUser.getText().toString();
        } else {
            nameUser.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!phoneUser.getText().toString().equals("") || phoneUser.getText() != null) {
            phone_user = phoneUser.getText().toString();
        } else {
            phoneUser.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        imgUrl = "";

        if(!address.getText().toString().equals("") && !porodaAnim.getText().toString().equals("")&&!datePropazhi.getText().toString().equals("")
                && !opisanie.getText().toString().equals("") && !pol.getText().toString().equals("") && !nameAnim.getText().toString().equals("")
                && !nameUser.getText().toString().equals("") && !phoneUser.getText().toString().equals("") )
            addToPoteryashkaDataBase(adress_potery, poroda_anim, date_prodazhi, pol_anim, imgUrl, opisanie_anim, name_anim, name_user, phone_user);
        });
    }

    private void addAnimals() {

        mesto.setText("Место нахождения");
        poroda.setVisibility(View.GONE);
        klichka.setVisibility(View.GONE);
        date.setVisibility(View.GONE);
        user.setVisibility(View.GONE);
        addZapis.setOnClickListener(v -> {
        if (!address.getText().toString().equals("") || address.getText() != null) {
            adress_potery = address.getText().toString();
        } else {
            address.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!pol.getText().toString().equals("") || pol.getText() != null) {
            pol_anim = pol.getText().toString();
        } else {
            pol.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!opisanie.getText().toString().equals("") || opisanie.getText() != null) {
            opisanie_anim = opisanie.getText().toString();
        } else {
            opisanie.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        imgUrl = "";

            if (!address.getText().toString().equals("") && !pol.getText().toString().equals("") && !opisanie.getText().toString().equals(""))
                addToAnimalsDataBase(adress_potery, pol_anim, imgUrl, opisanie_anim);
        });
    }
}
