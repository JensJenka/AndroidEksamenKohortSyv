package com.example.androideksamenkohortsyv

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class Fragment2(val pictureArray: ArrayList<Picture>) : Fragment() {

    lateinit var imageView: ImageView
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
        Log.i(Globals.TAG, "Display Picture from array, index location: " + index)

        val imageView: ImageView = view.findViewById<ImageView>(R.id.imageView)

        var image: Bitmap = if (uploadedPicture.imageUri != null)
            getBitmap(requireContext(), null, uploadedPicture.imageUri, ::UriToBitmap)
        else getBitmap(requireContext(), R.drawable.ic_launcher_foreground, null, ::VectorDrawableToBitmap)

        imageView.setImageBitmap(image)


        //------------------------^FUNKER------------------------------------------------------------------------//
        val recyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        var onItemClickListener = object: View.OnClickListener {
            override fun onClick(view: View?) {

                val position: Int = view?.tag.toString().toInt()
                pictureArray.get(position)

                itemAdapter?.notifyDataSetChanged()
            }
        }

        var onItemKeepListener = object: View.OnClickListener {
            override fun onClick(view: View?) {

                val position: Int = view?.tag.toString().toInt()
                val selectedPicture: Picture = Picture(position.toString())
                selectedPicture.position = position

                val intent: Intent = Intent(activity, EditActivity::class.java)
                intent.putExtra("onItemKeepListener TEZT", selectedPicture)
                startForResult.launch(intent)
            }
        }

        itemAdapter = GalleryAdapter(pictureArray, onItemClickListener, onItemKeepListener)
        recyclerView.setAdapter(itemAdapter)

        return view
    }

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val updatePicture: Picture = (intent?.getSerializableExtra("selected_student") as Picture)

            pictureArray.set(updatePicture.position, updatePicture)

            itemAdapter?.notifyDataSetChanged()
        }
    }

}