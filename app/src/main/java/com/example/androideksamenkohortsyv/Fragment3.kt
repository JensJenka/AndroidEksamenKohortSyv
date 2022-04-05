package com.example.androideksamenkohortsyv

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

class Fragment3 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(Globals.TAG, "Fragment 3 onCreate")
        Toast.makeText(activity, "Fragment 3 onCreate", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(Globals.TAG, "Fragment 3 onCreateView")
        Toast.makeText(activity, "Fragment 3 onCreateView", Toast.LENGTH_SHORT).show()
        return inflater.inflate(R.layout.fragment3, container, false)
    }

}