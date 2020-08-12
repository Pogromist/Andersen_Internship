package com.example.andersen_internship.roomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.andersen_internship.R
import kotlinx.android.synthetic.main.item_movie_data.view.*

class MovieDataAdapter(private val onDeleteClick: (MovieData) -> Unit) :
    RecyclerView.Adapter<MovieDataAdapter.DataViewHolder>() {
    private var movieDataList = mutableListOf<MovieData>()

    fun setData(list: List<MovieData>) {
        movieDataList.clear()
        movieDataList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_data, parent, false)
        ) {
            onDeleteClick.invoke(it)
        }
    }

    override fun getItemCount(): Int {
        return movieDataList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.setData(movieDataList[position])
    }

    inner class DataViewHolder(
        itemView: View, val onDeleteClick: (MovieData) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        fun setData(movieData: MovieData) {
            itemView.apply {
                txtTitle.text = "Title : ${movieData.title}"
                txtDuration.text = "Duration : ${movieData.duration.toString()}"
                imgDelete.setOnClickListener {
                    onDeleteClick(movieData)
                }
            }
        }
    }
}