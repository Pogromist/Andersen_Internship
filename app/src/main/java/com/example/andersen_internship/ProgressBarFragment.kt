package com.example.andersen_internship

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_progressbar.*

class ProgressBarFragment : Fragment() {

    var itemList = ArrayList<ProgressModel>()
    val progressAdapter: ProgressAdapter = ProgressAdapter(itemList)
    var count = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_progressbar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerViewProgressBar.layoutManager = LinearLayoutManager(this.context)
        progressAdapter.progressModelList = itemList
        recyclerViewProgressBar.adapter = progressAdapter

        btnAddProgressBar.setOnClickListener {
            count++
            addData()
        }
    }

    private fun addData() {
        itemList.add(ProgressModel(R.id.progressBar))
        progressAdapter.notifyItemInserted(count)
    }
}