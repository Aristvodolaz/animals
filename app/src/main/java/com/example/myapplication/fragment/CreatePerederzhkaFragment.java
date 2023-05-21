package com.example.myapplication.fragment;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.CreateFormAnimalsActivity;
import com.example.myapplication.activity.StartActivity;
import com.example.myapplication.db.Animals;
import com.example.myapplication.db.NeedPerderzhka;
import com.example.myapplication.db.Perederzhka;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreatePerederzhkaFragment extends Fragment {
    TabLayout tl;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseFirestore db;
    ImageView addInfo;

    LinearLayout price;
    Button addZapis, addPhoto;

    RecyclerView rv;

    String name,surname, phone, address, animals,
            poroda, descriprion, imgUrl, days, priceStr;
    String day;
    ImageView backArrow;
    EditText nameTv,surnameTv, phoneTv, addressTv, animalsTv, porodaTv, descriprionTv, imgUrlTv, priceTv, daysTv;
    String[] labels = {"МЫ ИЩЕМ ПЕРЕДЕРЖКУ","МЫ ПРЕДЛАГАЕМ ПЕРЕДЕРЖКУ"};
    //todo sddelat formu dlya perederzhki
    public static CreatePerederzhkaFragment newInstance() {
        return new CreatePerederzhkaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.create_perederzhka_layout, container, false);
        db = FirebaseFirestore.getInstance();
        tl = v.findViewById(R.id.tab_view);
        tl.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.color_for_reg));

        tl.setTabIndicatorFullWidth(true);

        price = v.findViewById(R.id.price_line);

        nameTv = v.findViewById(R.id.name_uesr);
        surnameTv = v.findViewById(R.id.surname_uesr);
        phoneTv = v.findViewById(R.id.phone_user);
        animalsTv = v.findViewById(R.id.animals_type);
        porodaTv = v.findViewById(R.id.poroda_anim);
        daysTv = v.findViewById(R.id.num_day);
        addressTv = v.findViewById(R.id.user_addres);

        priceTv = v.findViewById(R.id.price);

        addPhoto = v.findViewById(R.id.add_photo);
        addZapis = v.findViewById(R.id.add_zapis);

        backArrow = v.findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), StartActivity.class);
            startActivity(intent);
        });
        initViewPager();
        addPerederzhka();
        return v;
    }
    private void initViewPager() {
        for (int i = 0; i < labels.length; i++) {
            tl.addTab(tl.newTab().setText(labels[i]));
        }

        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        addPerederzhka();
                        break;
                    case 1:
                        addNeedPerederzhka();
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

    private void addToNeedDataBase(String name, String surname, String phone,
                                      String animals, String poroda, String days, String address) {

        CollectionReference dbDrivers = db.collection("PerederzhkaAnimalsNeedData");
        NeedPerderzhka needPerderzhka = new NeedPerderzhka(name, surname, phone,animals, poroda, days, address);
        dbDrivers.add(needPerderzhka).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

    private void addToDataBase(String name, String surname, String phone, String city,
                               String type_animals, String description, String imgURL, String price) {

        CollectionReference dbDrivers = db.collection("PerederzhkaAnimalsData");
        Perederzhka perederzhka = new Perederzhka(name, surname, phone, city, type_animals, description, imgURL, price);
        dbDrivers.add(perederzhka).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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
    private void addNeedPerederzhka() {

        price.setVisibility(View.GONE);
        addZapis.setOnClickListener(view -> {
            if (!nameTv.getText().toString().equals("") || nameTv.getText() != null) {
                name = nameTv.getText().toString();
            } else {
                nameTv.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if (!surnameTv.getText().toString().equals("") || surnameTv.getText() != null) {
                surname = surnameTv.getText().toString();
            } else {
                surnameTv.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if (!phoneTv.getText().toString().equals("") || phoneTv.getText() != null) {
                phone = phoneTv.getText().toString();
            } else {
                phoneTv.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if (!animalsTv.getText().toString().equals("") || animalsTv.getText() != null) {
                animals = animalsTv.getText().toString();
            } else {
                animalsTv.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if (!porodaTv.getText().toString().equals("") || porodaTv.getText() != null) {
                poroda = porodaTv.getText().toString();
            } else {
                porodaTv.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if (!daysTv.getText().toString().equals("") || daysTv.getText() != null) {
                days = daysTv.getText().toString();
            } else {
                daysTv.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if (!addressTv.getText().toString().equals("") || addressTv.getText() != null) {
                address = addressTv.getText().toString();
            } else {
                addressTv.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }

            imgUrl = "a";
            if(!nameTv.getText().toString().equals("") && !surnameTv.getText().toString().equals("") &&
                    !phoneTv.getText().toString().equals("") && !animalsTv.getText().toString().equals("") &&
                    !porodaTv.getText().toString().equals("") && !daysTv.getText().toString().equals("")
                    && !addressTv.getText().toString().equals(""))
            addToNeedDataBase(name, surname, phone,animals,poroda, day, address);
        });


    }

    private void addPerederzhka() {

        price.setVisibility(View.VISIBLE);

        addZapis.setOnClickListener(view -> {


        if (!nameTv.getText().toString().equals("") || nameTv.getText() != null) {
            name = nameTv.getText().toString();
        } else {
            nameTv.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!surnameTv.getText().toString().equals("") || surnameTv.getText() != null) {
            surname = surnameTv.getText().toString();
        } else {
            surnameTv.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!phoneTv.getText().toString().equals("") || phoneTv.getText() != null) {
            phone = phoneTv.getText().toString();
        } else {
            phoneTv.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!animalsTv.getText().toString().equals("") || animalsTv.getText() != null) {
            animals = animalsTv.getText().toString();
        } else {
            animalsTv.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!porodaTv.getText().toString().equals("") || porodaTv.getText() != null) {
            poroda = porodaTv.getText().toString();
        } else {
            porodaTv.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        if (!daysTv.getText().toString().equals("") || daysTv.getText() != null) {
            days = daysTv.getText().toString();
        } else {
            daysTv.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }

        imgUrl = "a";
        if(!nameTv.getText().toString().equals("") && !surnameTv.getText().toString().equals("") &&
               !phoneTv.getText().toString().equals("") && !animalsTv.getText().toString().equals("") &&
                !porodaTv.getText().toString().equals("") && !daysTv.getText().toString().equals("")
                && !priceTv.getText().toString().equals(""))
        addToDataBase(name, surname, phone, animals, poroda, days, imgUrl, priceStr);
        });
    }
}
