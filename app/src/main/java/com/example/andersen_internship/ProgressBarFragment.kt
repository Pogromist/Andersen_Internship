package com.example.andersen_internship

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_progressbar.*
import java.lang.Thread.sleep

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
        btnAsyncTask.setOnClickListener {
            MyAsyncTask().execute()
        }
        btnLoader.setOnClickListener {
        }
        btnService.setOnClickListener {
        }
        btnHandler.setOnClickListener {
        }
    }

    private fun addData() {
        itemList.add(ProgressModel(R.id.progressBar))
        progressAdapter.notifyItemInserted(count)
    }

    inner class MyAsyncTask : AsyncTask<Int?, Int?, Void>() {
        private var count = 0

        override fun onPreExecute() {
            Toast.makeText(context, "Task Starting", LENGTH_SHORT).show()
        }

        override fun onProgressUpdate(vararg values: Int?) {
            progressBarAsync.progress = values[0]!!
        }

        override fun doInBackground(vararg params: Int?): Void? {
            while (count < 100) {
                count++
                sleep(5)
                publishProgress(count)
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            Toast.makeText(context, "Task Completed", LENGTH_SHORT).show()
        }
    }
}