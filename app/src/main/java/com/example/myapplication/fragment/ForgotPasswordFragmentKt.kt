package com.example.myapplication.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.activity.MainActivity
import com.google.firebase.auth.FirebaseAuth


class ForgotPasswordFragmentKt : Fragment() {
    private val TAG = "ForgotPasswordActivityKt"

    private var resEmail: EditText? = null

    private var mAuth: FirebaseAuth? = null
    companion object {
        fun newInstance(): ForgotPasswordFragmentKt? {
            return ForgotPasswordFragmentKt()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forgot_password_kt, container, false)

        val backArr: ImageView = view.findViewById(R.id.back_arrow)
        backArr.setOnClickListener{
            (activity as MainActivity?)!!.replaceFragment(StartFragmentKt.newInstance(), true)
        }

        val resetBtn: Button = view.findViewById(R.id.btn_forgot)
        resetBtn.setOnClickListener{
            initialise()
        }
        return view
    }

    private fun initialise(){
        resEmail = view?.findViewById(R.id.login_text) as EditText

        mAuth = FirebaseAuth.getInstance()

        sendPasswordResetEmail()
    }

    private fun sendPasswordResetEmail(){
        val email = resEmail?.text.toString()

        if(!TextUtils.isEmpty(email)){
            mAuth!!.sendPasswordResetEmail(email).
            addOnCompleteListener {
                    task ->
                if(task.isSuccessful){
                    val message = "КОд отправлен на почту."
                    Log.d(TAG, message)
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show()
                    updateUI()
                } else{
                    Log.w(TAG, "message", task.exception)
                    Toast.makeText(getContext(), "Пользователь с данной почтой не найден", Toast.LENGTH_SHORT).show()
                }
            }
        } else{
            Toast.makeText(getContext(), "Введите почту", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(){
        (activity as MainActivity?)!!.replaceFragment(LoginFragmentKt.newInstance(), true)
    }
}