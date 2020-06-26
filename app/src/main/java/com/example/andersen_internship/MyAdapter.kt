package com.example.andersen_internship

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

private const val POST_TYPE_TEXT = 0
private const val POST_TYPE_IMAGE = 1

class MyAdapter (var objectListItems: List<DataModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == POST_TYPE_TEXT) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.text_row, parent, false)
            return TextViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.img_row, parent, false)
            return ImageViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == POST_TYPE_TEXT) {
            (holder as TextViewHolder).bind(objectListItems[position])
        } else {
            (holder as ImageViewHolder).bind(objectListItems[position])
        }
    }

    override fun getItemCount(): Int {
        return objectListItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(objectListItems[position].post_type == 0) {
            POST_TYPE_TEXT
        } else
            POST_TYPE_IMAGE
    }
}