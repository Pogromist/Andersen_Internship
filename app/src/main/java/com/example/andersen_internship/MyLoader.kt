package com.example.andersen_internship

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.loader.content.AsyncTaskLoader

class MyLoader(context: Context) : AsyncTaskLoader<Void>(context) {
    private var count = 0

    override fun onStartLoading() {
        Toast.makeText(context, "Loader starting", LENGTH_SHORT).show()
        forceLoad()
    }

    override fun loadInBackground(): Void? {
        while (count < 100) {
            count++
            Thread.sleep(5)
        }
        return null
    }
}