package com.example.androideksamenkohortsyv

import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.edmodo.cropper.CropImageView

class UploadActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment1)

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

        }
        imageView.setImageBitmap(image)

    }


}