package com.example.andersen_internship

import android.os.Handler
import android.os.HandlerThread
import android.os.Process

class MyHandlerThread() : HandlerThread("MyHandlerThread", Process.THREAD_PRIORITY_BACKGROUND) {
    private var handler = Handler()

    fun postTask (task: Runnable) {
        handler.post(task)
    }

    fun prepareHandler() {
        handler = Handler(looper)
    }
}