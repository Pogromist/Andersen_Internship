package com.example.andersen_internship

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.text_row.view.*

class TextViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind (dataModel: DataModel) {
        itemView.textView.text = dataModel.text
    }
}