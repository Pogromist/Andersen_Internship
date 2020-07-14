package com.example.andersen_internship

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.andersen_internship.ProgressBarFragment.Companion.INTENT_KEY
import java.lang.Thread.sleep


class MyService() : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread (Runnable {
            loading()
        }).start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun loading() {
        for (i in 0..100) {
            sleep(10)
            sendMessageToActivity(i)
        }
    }

    private fun sendMessageToActivity(msg: Int) {
        val intent = Intent(INTENT_KEY)
        intent.putExtra("key", msg)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }
}
