package com.example.andersen_internship

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.loader.content.AsyncTaskLoader
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
            MyLoader(requireContext()).startLoading()
        }
        btnService.setOnClickListener{
            MyService().onCreate()
        }
        btnHandler.setOnClickListener {
            MyHandler().sendMessage(MyHandler().obtainMessage())
        }
    }

    private fun addData() {
        itemList.add(ProgressModel(R.id.progressBar))
        progressAdapter.notifyItemInserted(count)
    }

    fun asyncTask () {
        progressBarAsync.progress = 0
        while (progressBarAsync.progress < 100) {
            progressBarAsync.progress++
            sleep(5)
        }
    }

    // Async Task
    inner class MyAsyncTask : AsyncTask<Void, Void, Void>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progressBarAsync.progress = 0
        }

        override fun doInBackground(vararg params: Void?): Void? {
            asyncTask()
            return null
        }
    }

    // Loader
    inner class MyLoader(context: Context) : AsyncTaskLoader<Void>(context) {
        override fun onStartLoading() {
            super.onStartLoading()
            loadInBackground()
        }

        override fun loadInBackground(): Void? {
            asyncTask()
            Toast.makeText(context, "Loader is finished", LENGTH_SHORT).show()
            return null
        }
    }

    // Service
    inner class MyService : Service() {
        override fun onBind(intent: Intent?): IBinder? {
            return null
        }

        override fun onCreate() {
            super.onCreate()
            asyncTask()
            progressBarAsync.progress++
            Toast.makeText(context, "Service is finished", LENGTH_SHORT).show()
        }
    }

    // Handler
    inner class MyHandler : Handler() {
        override fun handleMessage(msg: Message) {
            asyncTask()
            Toast.makeText(context, "Handler is finished", LENGTH_SHORT).show()
        }

    }
}