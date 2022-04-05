package com.example.androideksamenkohortsyv

import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class UploadActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment1)

        val uploadPicture : Picture = (intent.getSerializableExtra("TEST picture") as Picture)

        val image: Bitmap =
            if (uploadPicture.imageUri != null)
                getBitmap(this, null, uploadPicture.imageUri, ::UriToBitmap)
            else getBitmap(this, R.drawable.ic_launcher_foreground, null, ::VectorDrawableToBitmap)


    }


}