package com.example.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.activity.MainActivity


class StartFragmentKt : Fragment() {

    companion object {
        fun newInstance(): StartFragmentKt? {
            return StartFragmentKt()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.start_layout, container, false)

        val regBtn: Button = view.findViewById(R.id.reg_btn)
        regBtn.setOnClickListener {
            (activity as MainActivity?)!!.replaceFragment(ChoiceRegFragment.newInstance(), true)
        }

        val logBtn: Button = view.findViewById(R.id.init_btn)
        logBtn.setOnClickListener{
            (activity as MainActivity?)!!.replaceFragment(LoginFragmentKt.newInstance(), true)
        }
        return view
    }
}