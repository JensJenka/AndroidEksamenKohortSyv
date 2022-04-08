package com.example.androideksamenkohortsyv

import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.edmodo.cropper.CropImageView

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment1)

        val oldSelectedPicture :Picture = (intent.getSerializableExtra("selected_student") as Picture)


        val imageView: ImageView = findViewById<ImageView>(R.id.image)

        var image: Bitmap = if (oldSelectedPicture.imageUri != null)
            getBitmap(this, null, oldSelectedPicture.imageUri, ::UriToBitmap)
        else getBitmap(this, R.drawable.ic_launcher_foreground, null, ::VectorDrawableToBitmap)

        if (oldSelectedPicture.imageUri != null) {
            image = Bitmap.createBitmap(image)

        /*    image = Bitmap.createScaledBitmap(
                image,
                (resources.displayMetrics.density * 200).toInt(),
                (resources.displayMetrics.density * 200).toInt(),
                false
            )*/
        }
        imageView.setImageBitmap(image)

        val uploadButton: Button = findViewById<Button>(R.id.upload_btn)
        uploadButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                val updatedSelectedPicture: Picture = oldSelectedPicture.copy()


                val intent: Intent = Intent()
                intent.putExtra("selected_student", updatedSelectedPicture)
                setResult(RESULT_OK, intent);
                finish()
            }
        })
    }
}