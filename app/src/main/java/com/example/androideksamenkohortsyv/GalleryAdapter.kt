package com.example.androideksamenkohortsyv

import android.R.attr
import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.R.attr.data
import android.R.attr.keepScreenOn
import android.widget.*


class GalleryAdapter (
    val pictureArray: ArrayList<Picture>,
    val onItemClickListener: View.OnClickListener,
    val onItemKeepListener: View.OnClickListener
    ) : RecyclerView.Adapter<GalleryAdapter.ItemViewHolder>() {
    /*
    * This was the planed adapter, and things did not go according to plan
    * */

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        //-----------------------------------------------------------------------
        val ll1: LinearLayout = LinearLayout(parent.context)
        ll1.layoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT, 0.7f)

        val imageView: RecyclerView = RecyclerView(parent.context)
        imageView.setPadding(10, 0, 10, 0)

        ll1.addView(imageView)
        //-----------------------------------------------------------------------
        val ll2: LinearLayout = LinearLayout(parent.context)
        ll2.layoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT, 0.3f)

        val keepButton = Button(parent.context)
        keepButton.setText("Keep")

        ll2.addView(keepButton)
        //-----------------------------------------------------------------------
        val ll3: LinearLayout = LinearLayout(parent.context)
        ll3.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        ll3.weightSum = 1.0f

        ll3.addView(ll2)
        ll3.addView(ll1)

        return ItemViewHolder(ll3)
    }



    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val pictureInfo: Picture = pictureArray.get(position)

        holder.itemView.setTag(position)
        //----DENNE MÅÅÅ FIKSES
        //(((holder.itemView as LinearLayout).getChildAt(0) as LinearLayout).getChildAt(0))


        ((holder.itemView as LinearLayout).getChildAt(0) as LinearLayout).setOnClickListener{
            onItemClickListener.onClick(holder.itemView)
        }

        (((holder.itemView as LinearLayout).getChildAt(0) as LinearLayout).setOnClickListener{
                onItemKeepListener.onClick(holder.itemView)
            })
        // (((holder.itemView as LinearLayout).getChildAt(0) as LinearLayout).getChildAt(0) )
/*
        ((holder.itemView as LinearLayout).getChildAt(0) as LinearLayout).setOnClickListener{
            onItemClickListener.onClick(holder.itemView)
        }*/


        //        (((holder.itemView as LinearLayout).getChildAt(0) as ScrollView).getChildAt(0) as RecyclerView)
//        (((holder.itemView as LinearLayout).getChildAt(0) as RecyclerView).getChildAt(0) as ImageView)
        /*  var root: LinearLayout = view.findViewById<ScrollView>(R.id.imageSearchScreen).getChildAt(0) as LinearLayout
          var root1: ScrollView = view.findViewById<LinearLayout>(R.id.imageSearchScreen).getChildAt(0) as ScrollView
          var root2: ScrollView = view.findViewById<LinearLayout>(R.id.imageSearchScreen).getChildAt(0) as ScrollView*/


                //imageSearchScreen == root of fragment




        /*     holder. = pictureData.imageUri*/


/*        holder.itemView.setTag(position)
        (((holder.itemView as LinearLayout).getChildAt(0) as RecyclerView).getChildAt(0) as ImageView)

        ((holder.itemView as LinearLayout).getChildAt(0) as LinearLayout).setOnClickListener{
            onItemClickListener.onClick(holder.itemView)
        }*/

     /*   (((holder.itemView as LinearLayout).getChildAt(1) as LinearLayout).getChildAt(0) as Button).setOnClickListener{
            onItemEditListener.onClick(holder.itemView)
        }*/

    }

    override fun getItemCount(): Int {
        return pictureArray.size
    }


}