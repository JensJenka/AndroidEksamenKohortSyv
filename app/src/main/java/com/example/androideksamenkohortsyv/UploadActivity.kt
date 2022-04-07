package com.example.androideksamenkohortsyv

import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.edmodo.cropper.CropImageView
import android.content.Intent
import android.widget.Button

import androidx.core.app.ActivityCompat.startActivityForResult




class UploadActivity : AppCompatActivity(){

    private var imageView: ImageView? = null
    private val SELECT_PICTURE = 100

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment1)
        imageView = findViewById(R.id.image)
/*
        var image = view?.findViewById<ImageView>(android.R.id.image)
        var selectButton = findViewById(R.id.select_btn)
        findViewById(R.id.select_btn).setOnClickListener(View.OnClickListener { openImageChooser()

        })

        val uploadPicture : Picture = (intent.getSerializableExtra("TEST picture") as Picture)

        val imageView: ImageView = findViewById<ImageView>(R.id.image)

        var image: Bitmap =
            if (uploadPicture.imageUri != null)
                getBitmap(this, null, uploadPicture.imageUri, ::UriToBitmap)
            else getBitmap(this, R.drawable.ic_launcher_foreground, null, ::VectorDrawableToBitmap)

        if (uploadPicture.imageUri != null) {
            image = Bitmap.createBitmap(
                image,
                uploadPicture.x,
                uploadPicture.y,
                uploadPicture.w,
                uploadPicture.h
            )

        }*/
       // imageView.setImageBitmap(image)

    }

    fun openImageChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE)
    }


}