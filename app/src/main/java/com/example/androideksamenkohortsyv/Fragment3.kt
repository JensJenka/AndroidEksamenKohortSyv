package com.example.androideksamenkohortsyv

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class Fragment3(val pictureArray: ArrayList<Picture>) : Fragment() {
/*
* This fragment was planed to be in use, but are not used in the final product
* There were problems attaching the receiving images to the adapter
* Resulting in us raither just outouting the results in the 'Run' window
* */

    lateinit var imageView: ImageView
    var itemAdapter: GalleryAdapter? = null

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

        val view = inflater.inflate(R.layout.fragment2, container, false)

        val recyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        var onItemClickListener = object: View.OnClickListener {
            override fun onClick(view: View?) {
                val position: Int = view?.tag.toString().toInt()
                pictureArray.get(position)

            }
        }

        var onItemKeepListener = object: View.OnClickListener {
            override fun onClick(view: View?) {

                val position: Int = view?.tag.toString().toInt()
                val selectedPicture: Picture = pictureArray.get(position)
                selectedPicture.position = position
            }
        }
        itemAdapter = GalleryAdapter(pictureArray, onItemClickListener, onItemKeepListener)
        recyclerView.setAdapter(itemAdapter)
        return view
    }

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val updatedStudentInfo: Picture = (intent?.getSerializableExtra("selected_student") as Picture)

            pictureArray.set(updatedStudentInfo.position, updatedStudentInfo)

            itemAdapter?.notifyDataSetChanged()
        }
    }
}