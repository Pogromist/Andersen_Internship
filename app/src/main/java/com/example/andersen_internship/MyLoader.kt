package com.example.andersen_internship

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.loader.content.AsyncTaskLoader
import kotlinx.android.synthetic.main.fragment_progressbar.*

class MyLoader(context: Context, var progressBarFragment: ProgressBarFragment) : AsyncTaskLoader<Void>(context) {
    private var count = 0

    override fun onStartLoading() {
        Toast.makeText(context, "Loader starting", LENGTH_SHORT).show()
        forceLoad()
    }

    override fun loadInBackground(): Void? {
        progressBarFragment.progressBarAsync.progress = 0
        while (count < 100) {
            count++
            progressBarFragment.progressBarAsync.progress++
            Thread.sleep(5)
        }
        return null
    }
}