package com.example.androideksamenkohortsyv

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment




class Fragment1 : Fragment() {

    public lateinit var imageView: ImageView
    public lateinit var button: Button
    public var imageUri: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Log.i(Globals.TAG, "Fragment 1 onCreate")
        Toast.makeText(activity, "Fragment 1 onCreate", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(Globals.TAG, "Fragment 1 onCreateView")
        Toast.makeText(activity, "Fragment 1 onCreateView", Toast.LENGTH_SHORT).show()

        var view = inflater.inflate(R.layout.fragment1, container, false)

        imageView = view.findViewById<ImageView>(R.id.imageView)
        imageView.setOnClickListener(View.OnClickListener {
            var i = Intent()
            i.type = "*/*"
            i.action = Intent.ACTION_GET_CONTENT

            startForResult.launch(i)
        })


        return view
    }

    var startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

        imageUri = it.data?.data.toString()

        var bitmap_image = getBitmap(requireContext(), null, imageUri, ::UriToBitmap)

        imageView.layoutParams = imageView.layoutParams.apply {

            width = bitmap_image.width
            height = bitmap_image.height
        }

        imageView.setImageBitmap(bitmap_image)
        imageView.background = BitmapDrawable(resources, bitmap_image)
    }
}