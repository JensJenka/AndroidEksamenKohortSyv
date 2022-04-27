package com.example.androideksamenkohortsyv

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class Fragment2(val pictureArray: ArrayList<Picture>, val responseLinkArray:ArrayList<String>) : Fragment() {

    lateinit var imageView: ImageView
    lateinit var button: Button
    var itemAdapter: GalleryAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(Globals.TAG, "Fragment 2 onCreate")
        Toast.makeText(activity, "Fragment 2 onCreate", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(Globals.TAG, "Fragment 2 onCreateView")
        Toast.makeText(activity, "Fragment 2 onCreateView", Toast.LENGTH_SHORT).show()

        val view = inflater.inflate(R.layout.fragment2, container, false)

        //val imageView = view.findViewById<ImageView>(R.id.imageView)

        val index = pictureArray.lastIndex
        val uploadedPicture = pictureArray[index]
        Log.i(
            Globals.TAG,
            "Display Picture from array, index location: " + index + ", with URI: " + responseLinkArray[index]
        )

        val imageView: ImageView = view.findViewById<ImageView>(R.id.imageView)

        var image: Bitmap = if (uploadedPicture.imageUri != null)
            getBitmap(requireContext(), null, uploadedPicture.imageUri, ::UriToBitmap)
        else getBitmap(
            requireContext(),
            R.drawable.ic_launcher_foreground,
            null,
            ::VectorDrawableToBitmap
        )

        imageView.setImageBitmap(image)

        return view
    }
}