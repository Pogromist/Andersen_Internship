package com.example.andersen_internship

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.img_row.view.*

class ImageViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind (dataModel: DataModel) {
        itemView.imgView.setImageResource(R.drawable.ic_home)
    }
}