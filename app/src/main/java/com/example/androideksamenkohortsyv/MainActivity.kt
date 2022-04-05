package com.example.androideksamenkohortsyv

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private var pictureArray = ArrayList<Picture>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun switchFragment(v: View) {
        Log.i(Globals.TAG, "Activity 1 switchFragment. Tag: " + v.getTag().toString())
        Toast.makeText(
            this,
            "Activity switchFragment. Tag" + v.getTag().toString(),
            Toast.LENGTH_SHORT
        ).show()

        fragmentManager = supportFragmentManager
        pictureArray = ArrayList<Picture>()

       if (Integer.parseInt(v.getTag().toString()) == 1) {
            fragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_main,
                    Fragment1(),
                    "Fragment1"
                )
                .commit()
        } else if(Integer.parseInt(v.getTag().toString()) == 2){
           fragmentManager
               .beginTransaction()
               .replace(
                   R.id.fragment_main,
                   Fragment2(),
                   "Fragment2"
               )
               .commit()

        } else {
            fragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_main,
                    Fragment3(pictureArray),
                    "Fragment3"
                )
                .commit()
        }
    }

    /*fun upload(view: View) {
        var imageUri =
            (fragmentManager.findFragmentByTag("Fragment1") as Fragment1).imageUri.toString()

        var rect = (fragmentManager.findFragmentByTag("Fragment1") as Fragment1).actualCropRect!!
        var imgW = (fragmentManager.findFragmentByTag("Fragment1") as Fragment1).image.width
        var imgH = (fragmentManager.findFragmentByTag("Fragment1") as Fragment1).image.height

        val newPicture: Picture = Picture(
            imageUri,
            rect.left.toInt(),
            rect.top.toInt(),
            rect.right.toInt(),
            rect.bottom.toInt(),
            imgW.toInt(),
            imgH.toInt()
        )
        pictureArray.add(newPicture)

       /* dbHelper?.writableDatabase?.insert("students", null, ContentValues().apply {
            put("name", newStudent.name)
            put("surname", newStudent.surname)
            put(
                "image",
                bitmapTobyteArray(
                    getBitmap(
                        applicationContext,
                        null,
                        newStudent.imageUri,
                        ::UriToBitmap
                    )
                ).toByteArray()
            )
        })

        Toast.makeText(this, "Added New Student", Toast.LENGTH_SHORT).show() */
    }*/

}