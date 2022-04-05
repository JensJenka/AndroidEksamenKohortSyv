package com.example.androideksamenkohortsyv

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

class Fragment1 : Fragment() {

    public var imageUri: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(Globals.TAG, "Fragment 1 onCreate")
        Toast.makeText(activity, "Fragment 1 onCreate", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(Globals.TAG, "My fragment1 onCreateView")
        Toast.makeText(activity, "Toast Fragment 1 onCreateView", Toast.LENGTH_SHORT).show()

        var view = inflater.inflate(R.layout.fragment1, false)

        imageUri = view.findViewById<>(R.id.image)


    }

}