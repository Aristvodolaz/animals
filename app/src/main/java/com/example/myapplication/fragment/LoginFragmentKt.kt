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

class LoginFragmentKt : Fragment() {
    private val TAG = "LoginActivity"

    private var email: String? = null
    private var password: String? = null

    private var login: EditText? = null
    private var nPassword: EditText? = null
    private var btnLogin: Button? = null
    private var btnSignIn: TextView? = null
    private var forgotPassword: TextView? = null
    private var mProgressBar: ProgressDialog? = null

    private var mAuth: FirebaseAuth? = null


    companion object {
        fun newInstance(): LoginFragmentKt? {
            return LoginFragmentKt()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login_kt, container, false)

        val backArr: ImageView = view.findViewById(R.id.back_arrow)
        backArr.setOnClickListener{
            (activity as MainActivity?)!!.replaceFragment(StartFragmentKt.newInstance(), true)
        }
        val btnLog: Button = view.findViewById(R.id.btn_log)
        btnLog.setOnClickListener{
            initialise()
        }
        val regNowBtn: TextView = view.findViewById(R.id.regNowBtn)
        regNowBtn.setOnClickListener{
            (activity as MainActivity?)!!.replaceFragment(RegistrationFragmentKt.newInstance(), true)
        }
        val forgotPass: TextView = view.findViewById(R.id.forgPass)
        forgotPass.setOnClickListener{
            (activity as MainActivity?)!!.replaceFragment(ForgotPasswordFragmentKt.newInstance(), true)
        }

        return view
    }

    private fun initialise(){
        login = view?.findViewById(R.id.login_text) as EditText
        nPassword = view?.findViewById(R.id.pass_text) as EditText
        btnSignIn = view?.findViewById(R.id.regNowBtn) as TextView
        forgotPassword = view?.findViewById(R.id.forgPass) as TextView
        mProgressBar = ProgressDialog(getContext())

        mAuth = FirebaseAuth.getInstance()

        btnSignIn!!.setOnClickListener{
            (activity as MainActivity?)!!.replaceFragment(RegistrationFragmentKt.newInstance(), true)
        }

        loginUser()
    }

    private fun loginUser(){
        email = login?.text.toString()
        password = nPassword?.text.toString()

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            mProgressBar!!.setMessage("register user...")
            mProgressBar!!.show()

            Log.d(TAG, "Logging in user")

            mAuth!!.signInWithEmailAndPassword(email!!, password!!).
            addOnCompleteListener {
                    task ->
                mProgressBar!!.hide()

                if(task.isSuccessful){
                    Log.d(TAG, "signInWithEmail:success")
                    updateUI()
                } else{
                    Log.e(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show()

                }
            }
        } else{
            Toast.makeText(getContext(), "Enter all details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(){
        val intent = Intent(getContext(), StartActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}