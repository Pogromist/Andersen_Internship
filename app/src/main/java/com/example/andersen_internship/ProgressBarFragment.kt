package com.example.andersen_internship

import android.annotation.SuppressLint
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_progressbar.*
import java.lang.Thread.sleep

class ProgressBarFragment : Fragment(), LoaderManager.LoaderCallbacks<Void> {

    var itemList = ArrayList<ProgressModel>()
    val progressAdapter: ProgressAdapter = ProgressAdapter(itemList)
    var count = 0
    var flagLoader = false

    var myHandler = Handler()

    var myHandlerThread = MyHandlerThread()
    var handler = Handler()

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

        myHandler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                progressBarAsync.progress = msg.what
            }
        }

        var myRunnable = Runnable {
            for (i in 0..100) {
                sleep(5)
                handler.post(Runnable {
                    progressBarAsync.progress = i
                })
            }
        }

        myHandlerThread.start()
        myHandlerThread.prepareHandler()

        btnAddProgressBar.setOnClickListener {
            count++
            addData()
        }
        btnAsyncTask.setOnClickListener {
            MyAsyncTask().execute()
        }
        btnLoader.setOnClickListener {
            if (flagLoader)
                LoaderManager.getInstance(this).initLoader(1, null, this)
            else
                LoaderManager.getInstance(this).restartLoader(1, null, this)
        }
        btnService.setOnClickListener {

        }

        btnHandler.setOnClickListener {
            myHandlerThread.postTask(myRunnable)
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

    //AsyncTask Loader

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Void> {
        Toast.makeText(context, "Loader Start", LENGTH_SHORT).show()
        return MyLoader(this.requireContext(), myHandler)
    }

    override fun onLoadFinished(loader: Loader<Void>, data: Void?) {
        Toast.makeText(context, "Loader Finished", LENGTH_SHORT).show()
    }

    override fun onLoaderReset(loader: Loader<Void>) {}

}