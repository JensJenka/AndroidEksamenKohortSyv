package com.example.androideksamenkohortsyv

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import org.json.JSONObject
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.ByteArrayOutputStream
import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.concurrent.thread
import okhttp3.OkHttpClient
import okhttp3.Response





class MainActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private var pictureArray = ArrayList<Picture>()
    private var responseLinkArray = ArrayList<String>()

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
                   Fragment2(pictureArray, responseLinkArray),
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

        Log.i(Globals.TAG, "imageuri: " + imageUri)


        val newPicture: Picture = Picture(imageUri)
        pictureArray.add(newPicture)
        var bitmapImage = getBitmap(this, null, imageUri, ::UriToBitmap)

        val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-kkmmss"))
        val outputFile = File.createTempFile(timestamp, null, this.cacheDir)

        Log.i(Globals.TAG, "Eksisterer?:" + outputFile.exists())

        val imgOutputStream = ByteArrayOutputStream()
        bitmapImage.compress(Bitmap.CompressFormat.PNG, 50, imgOutputStream)
        val mediaType = "image/png".toMediaTypeOrNull()
        val req = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("image", "uploaded.png"
                , RequestBody.create(mediaType, imgOutputStream.toByteArray())).build()

        val urlBuilder = "http://api-edu.gtl.ai/api/v1/imagesearch/upload".toHttpUrlOrNull()!!.newBuilder()
        val url = urlBuilder.build().toString()
        val client = OkHttpClient()
        thread {
            val request = Request.Builder().url(url).post(req).build()
            val response = client.newCall(request).execute()
            val responseBody = response.body?.string()

            Log.i(Globals.TAG , "Responsbody, link: "+ responseBody.toString())
            var responscode = response.code
            Log.i(Globals.TAG, "Code: " + responscode)

            responseLinkArray.add(responseBody.toString())
            Log.i(Globals.TAG, "ResponseLinkArray ADD: " + responscode)
        }
    }

    fun performImageSearch(view: View) {
        val list = ArrayList<Picture>()
        Log.i(Globals.TAG, "perform SEARCH BUTTON")

        val index = responseLinkArray.lastIndex
        val uriToGET = responseLinkArray[index]

        val fullURL = "http://api-edu.gtl.ai/api/v1/imagesearch/bing?url=$uriToGET"
        //Log.i(Globals.TAG, "URI to GET: " + response)

        thread{
            Log.i(Globals.TAG, "within search thread")
            val client = OkHttpClient().newBuilder()
            .build()
        val request: Request = Request.Builder()
            .url(fullURL)
            .method("GET", null)
            .addHeader("accept", "application/json")
            .build()

        val response = client.newCall(request).execute()
        Log.i(Globals.TAG, "R:" + response.toString())
        }


/*
            var ccurrencies = JSONObject(response.body).getJSONArray("data")

            for (i in 0 until ccurrencies.length()){

                list.add(
                    CCurrencyStats(
                        ccurrencies.getJSONObject(i).getString("id"),
                        ccurrencies.getJSONObject(i).getString("rank"),
                        ccurrencies.getJSONObject(i).getString("symbol"),
                        ccurrencies.getJSONObject(i).getString("name"),
                        ccurrencies.getJSONObject(i).getString("supply"),
                        ccurrencies.getJSONObject(i).getString("maxSupply"),
                        ccurrencies.getJSONObject(i).getString("marketCapUsd"),
                        ccurrencies.getJSONObject(i).getString("volumeUsd24Hr"),
                        ccurrencies.getJSONObject(i).getString("priceUsd"),
                        ccurrencies.getJSONObject(i).getString("changePercent24Hr"),
                        ccurrencies.getJSONObject(i).getString("vwap24Hr"),
                        ccurrencies.getJSONObject(i).getString("explorer")))
            }

 */

    }

    private fun get(endpointURL: String): HTTPResponse {

        var url = URL(endpointURL)
        val connection = url.openConnection() as HttpURLConnection
        try {
            connection.requestMethod = "GET"

            connection.connect()

            val stream = if (connection.responseCode in 200..300) connection.inputStream else connection.errorStream
            val responseBody = try {
                stream.bufferedReader(Charsets.UTF_8).use {it.readText()}
            } catch (e: Throwable) {
                ""
            }

            return HTTPResponse(connection.responseCode, responseBody)
        }catch (e: Throwable) {
            return HTTPResponse(connection.responseCode, "")
        } finally {
            connection.disconnect()
        }
    }

    class HTTPResponse (private val statusCode: Int, val body: String) {
        val isSuccessful = statusCode in 200..300
    }


}






