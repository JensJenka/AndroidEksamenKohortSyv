package com.example.androideksamenkohortsyv

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GalleryAdapter (
    val picture: ArrayList<Picture>,
    val onItemClickListener: View.OnClickListener,
    val onItemEditListener: View.OnClickListener
    ) : RecyclerView.Adapter<GalleryAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val ll1: LinearLayout = LinearLayout(parent.context)
        ll1.layoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT, 0.7f)

        val ll2: LinearLayout = LinearLayout(parent.context)
        ll2.layoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT, 0.3f)

        val ll3: LinearLayout = LinearLayout(parent.context)
        ll3.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        ll3.weightSum = 1.0f

        ll3.addView(ll1)
        ll3.addView(ll2)

        return ItemViewHolder(ll3)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val pictureInfo: Picture = picture.get(position)

        holder.itemView.setTag(position)
        (((holder.itemView as LinearLayout).getChildAt(0) as LinearLayout).getChildAt(0) as ImageView).drawable

        ((holder.itemView as LinearLayout).getChildAt(0) as LinearLayout).setOnClickListener{
            onItemClickListener.onClick(holder.itemView)
        }

        (((holder.itemView as LinearLayout).getChildAt(1) as LinearLayout).getChildAt(0) as Button).setOnClickListener{
            onItemEditListener.onClick(holder.itemView)
        }

    }

    override fun getItemCount(): Int {
        return picture.size
    }


}