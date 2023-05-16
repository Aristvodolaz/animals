package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.activity.StartActivity;

public class AuthFragment extends Fragment {
    public static AuthFragment newInstance() {
        return new AuthFragment();
    }
    EditText login, pass;
    Button vhodBtn;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.login_layout, container, false);
        login = v.findViewById(R.id.login);
        pass = v.findViewById(R.id.password);
        vhodBtn = v.findViewById(R.id.vhod_btn);
        initViews();
        return  v;
    }
//todo dorabotat auth

    //todo аунтефикация в приложении и переход во внутрь
    private void initViews() {
        vhodBtn.setOnClickListener(v->{
            if (!login.getText().equals("") && !pass.getText().equals("")){
            } else if(!login.getText().equals("") && pass.getText().equals("")){
                Toast.makeText(getContext(), "Введите пароль!", Toast.LENGTH_SHORT).show();
            } else if (login.getText().equals("") && !pass.getText().equals("")){
                Toast.makeText(getContext(), "Введите логин!", Toast.LENGTH_SHORT).show();
            } else if(login.getText().equals("") && pass.getText().equals("")){
                Toast.makeText(getContext(), "Введите данные для входа в аккаунт!", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(getActivity(), StartActivity.class);
                i.putExtra("login", login.getText());
                startActivity(i);
            }
        });

    }
}
