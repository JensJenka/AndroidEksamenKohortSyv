package com.example.androideksamenkohortsyv

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.edmodo.cropper.CropImageView
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment1)

        val oldSelectedPicture :Picture = (intent.getSerializableExtra("selected_student") as Picture)
        Log.i(Globals.TAG, "In edit activity")


        val imageView: ImageView = findViewById<ImageView>(R.id.imageView)
        imageView.isEnabled = false;


        thread {
            var image: Bitmap? = null

            if (oldSelectedPicture.imageUri.toString().startsWith("http")) {
                with(URL(oldSelectedPicture.imageUri).openConnection() as HttpURLConnection) {
                    requestMethod = "GET"

                    setRequestProperty(
                        "User-Agent",
                        "Mozilla/5.0 (Macintosh; U; Intel Mac OS X; ja-JP-mac; rv:1.8.1.6) Gecko/20070725 Firefox/2.0.0.6"
                    )

                    val bm: Bitmap = BitmapFactory.decodeStream(inputStream)
                    Log.i(Globals.TAG, bm.toString())

                    imageView.post { imageView.setImageBitmap(bm) }
                }
            } else {
                image = if (oldSelectedPicture.imageUri != null)
                    getBitmap(
                        this,
                        null,
                        oldSelectedPicture.imageUri,
                        ::UriToBitmap
                    ) else getBitmap(
                    this,
                    R.drawable.ic_launcher_foreground,
                    null,
                    ::VectorDrawableToBitmap
                )

             /*   image = Bitmap.createScaledBitmap(
                    image,
                    oldSelectedPicture.imageH,
                    oldSelectedPicture.imageW,
                    false
                )*/

                if (oldSelectedPicture.imageUri != null) {
                    image = Bitmap.createBitmap(
                        image,
                    )

                    image = Bitmap.createScaledBitmap(
                        image,
                        (resources.displayMetrics.density * 200).toInt(),
                        (resources.displayMetrics.density * 200).toInt(),
                        false
                    )
                }

                imageView.post { imageView.setImageBitmap(image) }
            }

        }

/*        var image: Bitmap = if (oldSelectedPicture.imageUri != null)
            getBitmap(this, null, oldSelectedPicture.imageUri, ::UriToBitmap)
        else getBitmap(this, R.drawable.ic_launcher_foreground, null, ::VectorDrawableToBitmap)

        if (oldSelectedPicture.imageUri != null) {
            image = Bitmap.createBitmap(image)

        }
        imageView.setImageBitmap(image)*/

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