package com.example.androideksamenkohortsyv

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.VectorDrawable
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream
import java.io.Serializable

object Globals {
    val TAG = "ExamKohort7"
}

data class Picture(var imageUri: String?, var x: Int, var y: Int, var w: Int, var h: Int, var imageH: Int, var imageW: Int, var position: Int=-1):
    Serializable {
}

fun VectorDrawableToBitmap(context: Context, id: Int?, uri: String?) : Bitmap {
    val drawable = (ContextCompat.getDrawable(context!!, id!!) as VectorDrawable)
    val image = Bitmap.createBitmap(
        drawable.getIntrinsicWidth(),
        drawable.getIntrinsicHeight(),
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(image)
    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
    drawable.draw(canvas)

    return image
}

fun UriToBitmap(context: Context, id: Int?, uri: String?): Bitmap {
    val image: Bitmap = MediaStore.Images.Media.getBitmap(context!!.contentResolver, Uri.parse(uri))
    return image
}

fun getBitmap(context: Context, id: Int?, uri: String?, decoder: (Context, Int?, String?) -> Bitmap): Bitmap {
    return decoder(context, id, uri)
}

fun bitmapTobyteArray(image: Bitmap): ByteArrayOutputStream {
    val outputStream = ByteArrayOutputStream()
    image.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    return outputStream
}