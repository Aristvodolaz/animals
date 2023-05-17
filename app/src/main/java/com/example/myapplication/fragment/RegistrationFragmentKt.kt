package com.example.myapplication.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.myapplication.R
import com.example.myapplication.activity.MainActivity
import com.example.myapplication.activity.StartActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationFragmentKt : Fragment() {
    private var userName: EditText? = null
    private var userAge: EditText? = null
    private var userPhone: EditText? = null
    private var userEmail: EditText? = null
    private var userPassword: EditText? = null
    private var regButton: Button? = null
    private var progressBar: ProgressDialog? = null


    private var databaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private val TAG = "CreateAccountActivity"

    private var nickname: String? = null
    private var age: String? = null
    private var phone: String? = null
    private var email: String? = null
    private var password: String? = null

    companion object {
        fun newInstance(): RegistrationFragmentKt? {
            return RegistrationFragmentKt()
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
        userName = view?.findViewById(R.id.user_name) as EditText
        userAge = view?.findViewById(R.id.user_age) as EditText
        userPhone = view?.findViewById(R.id.user_phone) as EditText
        userEmail = view?.findViewById(R.id.user_email) as EditText
        userPassword = view?.findViewById(R.id.user_password) as EditText
        progressBar = ProgressDialog(getContext())

        mDatabase = FirebaseDatabase.getInstance()
        databaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()

        createNewAccount()
    }

    private fun createNewAccount(){
        nickname = userName?.text.toString()
        age = userAge?.text.toString()
        phone = userPhone?.text.toString()
        email = userEmail?.text.toString()
        password = userPassword?.text.toString()

        if(!TextUtils.isEmpty(nickname) && !TextUtils.isEmpty(age) &&
            !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(email) &&
            !TextUtils.isEmpty(password)){
            progressBar!!.setMessage("register user...")
            progressBar!!.show()

            mAuth!!.createUserWithEmailAndPassword(email!!, password!!).
            addOnCompleteListener(){
                    task -> progressBar!!.hide()

                if(task.isSuccessful){
                    Log.d(TAG, "createUserWithEmail:success")

                    val userId = mAuth!!.currentUser!!.uid

                    verifyEmail()

                    val currentUserIdBb = databaseReference!!.child(userId)
                    currentUserIdBb.child("nickname").setValue(nickname)
                    currentUserIdBb.child("age").setValue(age)
                    currentUserIdBb.child("phone").setValue(phone)
                    currentUserIdBb.child("email").setValue(email)
                    currentUserIdBb.child("password").setValue(password)

                    updateUserInfoAndUi()
                } else{
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show()
                }

            }
        } else{
            Toast.makeText(getContext(), "Enter all details", Toast.LENGTH_SHORT).show()
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