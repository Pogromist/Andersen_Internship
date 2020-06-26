package com.example.andersen_internship

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

class ProgressAdapter(var progressModelList: List<ProgressModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.progressbar_row, parent, false)
        return ProgressbarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return progressModelList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProgressbarViewHolder).bind(progressModelList[position])
    }
}