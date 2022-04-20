package com.example.androideksamenkohortsyv


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import okhttp3.RequestBody.Companion.toRequestBody
import androidx.fragment.app.FragmentManager
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import java.io.*
import java.net.URL
import java.nio.charset.Charset
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

import okhttp3.RequestBody

import okhttp3.OkHttpClient





class MainActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private var pictureArray = ArrayList<Picture>()
    var uploadedPictureArray = ArrayList<Picture>()

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
        } else if (Integer.parseInt(v.getTag().toString()) == 2) {
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

    fun upload(view: View) {
        Log.i(Globals.TAG, "Fragment 1 upload pushed")
        Toast.makeText(this, "Added New Picture", Toast.LENGTH_SHORT).show()

        var imageUri =
            (fragmentManager.findFragmentByTag("Fragment1") as Fragment1).imageUri.toString()


        val newPicture: Picture = Picture(imageUri)
        Log.i(Globals.TAG, "IMAGE URI:" + imageUri)

        var bitmapImage = getBitmap(this, null, imageUri, ::UriToBitmap)

        pictureArray.add(newPicture)

        val fileName = "tjomi.txt"
        var file = File(this.getCacheDir(), fileName)
        file.createNewFile()


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

        thread {
            val result = URL("http://api-edu.gtl.ai/api/v1/imagesearch/upload").readText()


            val payload = outputFile.toString()

            post(imageUri, "image")

        }



/*        thread {
            val result = URL("http://api-edu.gtl.ai/api/v1/imagesearch/upload").readText()
            val json = JSONArray(result)
            //val contentType = JSONArray("text/html; charset=UTF-8")

            for (index in 0 until json.length()){
                val imageUrl = (json.get(index) as JSONObject).getString("image_url")

                uploadedPictureArray.add(Picture(imageUrl,-1))
            }
        }*/
    }


    val JSON = "application/json; charset=utf-8".toMediaType()

    var client = OkHttpClient()

    @Throws(IOException::class)
    fun post(url: String, json: String): String {
        val body: RequestBody = json.toRequestBody(JSON)
        val request: Request = Request.Builder()
            .url(url)
            .post(body)
            .build()
        client.newCall(request).execute().use { response -> return response.body!!.string() }
    }
}






