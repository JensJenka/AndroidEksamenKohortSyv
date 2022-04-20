package com.example.androideksamenkohortsyv

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import java.net.URI

class RecyclerAdapter(val pictureArray: ArrayList<Picture>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pictureArray.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val picturePosition: Int = pictureArray[position].position

/*
        var test: Bitmap = getBitmap(this, null, )

        var image: Picture = pictureArray[0]
        if (pictureArray[picturePosition].imageUri != null) {
            image = Bitmap.createBitmap(image)

        }






        holder.pictureImage.setImageBitmap(image)*/
//[picturePosition]

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var pictureImage: ImageView
        var pictureKeepButton: Button

        init {
            pictureImage = itemView.findViewById(R.id.picture_image)
            pictureKeepButton = itemView.findViewById(R.id.keep_button)
        }
    }

}//Class braces