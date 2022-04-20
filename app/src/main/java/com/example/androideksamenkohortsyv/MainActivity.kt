package com.example.androideksamenkohortsyv

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.core.app.ActivityCompat.startActivityForResult
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream
import java.nio.charset.Charset
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


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
                   Fragment2(pictureArray),
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

    fun upload(view: View){
        Log.i(Globals.TAG, "Fragment 1 upload pushed")
        Toast.makeText(this, "Added New Picture", Toast.LENGTH_SHORT).show()

        var imageUri = (fragmentManager.findFragmentByTag("Fragment1") as Fragment1).imageUri.toString()

        val newPicture: Picture = Picture(imageUri)
        pictureArray.add(newPicture)
        var bitmapImage = getBitmap(this, null, imageUri, ::UriToBitmap)


        val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-kkmmss"))
        val outputFile = File.createTempFile(timestamp, null, this.cacheDir)

        if (outputFile.exists()) {
            outputFile.delete()
        }
        else {
            outputFile.parentFile?.mkdirs()
        }


        val input = bitmapImage.toString()
        val inputStream = ByteArrayInputStream(input.toByteArray(Charset.defaultCharset()))

        val outputStream = FileOutputStream(outputFile)

        inputStream.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }
        Log.i(Globals.TAG, "NYESTE BÆSJEN" + outputFile.toString() )
    }
}






