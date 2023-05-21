package com.example.myapplication.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.activity.MainActivity
import com.example.myapplication.activity.StartActivity
import com.example.myapplication.db.Users
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class RegistrationFragmentKt : Fragment() {
    private var userName: EditText? = null
    private var userSurname: EditText? = null
    private var userAge: EditText? = null
    private var userPhone: EditText? = null
    private var userEmail: EditText? = null
    private var userPassword: EditText? = null
    private var regButton: Button? = null
    private var progressBar: ProgressDialog? = null
    private var userDoublePass: EditText? = null
    private var userCity: EditText? = null

    private var dbUsers: CollectionReference? = null
    private var db: FirebaseFirestore? = null
    private var databaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private val TAG = "CreateAccountActivity"

    private var city: String? = null
    private var name: String? = null
    private var surname: String? = null
    private var age: String? = null
    private var phone: String? = null
    private var email: String? = null
    private var password: String? = null
    private var double_password: String? = null
    private var type: Int = 0

    companion object {
        val TYPE_INFO = "type_info"
        fun newInstance(type: Int): RegistrationFragmentKt? {
            val args = Bundle()
            args.putInt(TYPE_INFO, type)
            val fragment = RegistrationFragmentKt()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration_kt, container, false)

        val backArr: ImageView = view.findViewById(R.id.back_arrow)
        backArr.setOnClickListener{
            (activity as MainActivity?)!!.replaceFragment(StartFragmentKt.newInstance(), true)
        }

        val logNowBtn: TextView = view.findViewById(R.id.loginNowBtn)
        logNowBtn.setOnClickListener{
            (activity as MainActivity?)!!.replaceFragment(LoginFragmentKt.newInstance(), true)
        }

        val regBtn1: Button = view.findViewById(R.id.regBtn)
        regBtn1.setOnClickListener{
            initialise()
        }


        return view
    }

    private fun initialise(){
        userName = view?.findViewById(R.id.name) as EditText
        userSurname =  view?.findViewById(R.id.surname) as EditText
        userAge = view?.findViewById(R.id.user_age) as EditText
        userPhone = view?.findViewById(R.id.user_phone) as EditText
        userEmail = view?.findViewById(R.id.user_email) as EditText
        userPassword = view?.findViewById(R.id.user_password) as EditText
        userDoublePass = view?.findViewById(R.id.double_user_password) as EditText
        userCity = view?.findViewById(R.id.user_сity) as EditText
        progressBar = ProgressDialog(getContext())

        mDatabase = FirebaseDatabase.getInstance()
        databaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance();
        createNewAccount()
    }

    private fun createNewAccount(){
        name = userName?.text.toString()
        surname = userSurname?.text.toString()
        age = userAge?.text.toString()
        phone = userPhone?.text.toString()
        city = userCity?.text.toString()
        email = userEmail?.text.toString()
        password = userPassword?.text.toString()
        double_password = userDoublePass?.text.toString()
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(surname) && !TextUtils.isEmpty(age) &&
            !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(city) && !TextUtils.isEmpty(email) &&
            !TextUtils.isEmpty(password)
        ) {
        if(double_password.equals(password)) {


                progressBar!!.setMessage("Пожалуйста подождите")
                progressBar!!.show()

                mAuth!!.createUserWithEmailAndPassword(email!!, password!!)
                    .addOnCompleteListener() { task ->
                        progressBar!!.hide()

                        if (task.isSuccessful) {
                            Log.d(TAG, "createUserWithEmail:success")

                            val userId = mAuth!!.currentUser!!.uid

                            verifyEmail()
                            dbUsers = db?.collection("Users")
                            val currentUserIdBb = databaseReference!!.child(userId)
                            if(requireArguments().getInt(TYPE_INFO)==0){
                                currentUserIdBb.child("type").setValue(0)
                                type = 0
                            } else if (requireArguments().getInt(TYPE_INFO)==1){
                                //todo for doggiesitter
                                currentUserIdBb.child("type").setValue(1)
                                type = 1
                            } else if(requireArguments().getInt(TYPE_INFO)==2){
                                //todo for perederzhka
                                currentUserIdBb.child("type").setValue(2)
                                type = 2
                            }
                            currentUserIdBb.child("name").setValue(name)
                            currentUserIdBb.child("surname").setValue(surname)
                            currentUserIdBb.child("age").setValue(age)
                            currentUserIdBb.child("phone").setValue(phone)
                            currentUserIdBb.child("city").setValue(city)
                            currentUserIdBb.child("email").setValue(email)
                            currentUserIdBb.child("password").setValue(password)

                            val data = Users(type, name, surname, phone,
                                age,city,"https://cdn-icons-png.flaticon.com/512/1025/1025373.png")

                            dbUsers?.add(data)
                                ?.addOnSuccessListener(OnSuccessListener<DocumentReference?> {
                                    Toast.makeText(
                                        context,
                                        "Аккаунт успешно создан!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                })?.addOnFailureListener(OnFailureListener {
                                    Toast.makeText(
                                        context,
                                        "Произошла ошибка, попробуйте позднее!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                })
                            updateUserInfoAndUi()
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                getContext(),
                                "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
            }else{
            Toast.makeText(context, "Пароли не совпадают!", Toast.LENGTH_SHORT).show()
            userPassword?.backgroundTintList = ColorStateList.valueOf(Color.RED);
            userDoublePass?.backgroundTintList = ColorStateList.valueOf(Color.RED);
            }
        } else{
            Toast.makeText(getContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            if (TextUtils.isEmpty(name))
                userName?.backgroundTintList = ColorStateList.valueOf(Color.RED);
            if (TextUtils.isEmpty(surname))
                userSurname?.backgroundTintList = ColorStateList.valueOf(Color.RED);
            if (TextUtils.isEmpty(age))
                userAge?.backgroundTintList = ColorStateList.valueOf(Color.RED);
            if (TextUtils.isEmpty(phone))
                userPhone?.backgroundTintList = ColorStateList.valueOf(Color.RED);
            if (TextUtils.isEmpty(city))
                userCity?.backgroundTintList = ColorStateList.valueOf(Color.RED);
            if (TextUtils.isEmpty(email))
                userEmail?.backgroundTintList = ColorStateList.valueOf(Color.RED);
        }
    }

    private fun updateUserInfoAndUi(){
        val intent = Intent(getContext(), StartActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
    private fun verifyEmail(){
        val mUser = mAuth!!.currentUser
        mUser!!.sendEmailVerification().
        addOnCompleteListener() {
                task ->
            if(task.isSuccessful){
                Toast.makeText(getContext(), "Verification email sent to " +
                        mUser.getEmail(), Toast.LENGTH_SHORT).show()
            } else{
                Log.e(TAG, "sendEmailVerification", task.exception)
                Toast.makeText(getContext(), "Failed to send verification email",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}