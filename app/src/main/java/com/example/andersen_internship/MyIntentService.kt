package com.example.andersen_internship

import android.app.IntentService
import android.content.Intent
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.lang.Thread.sleep

class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        loading()
    }

    private fun loading() {
        for (i in 0..100) {
            sleep(10)
            sendMessageToActivity(i)
        }
    }

    private fun sendMessageToActivity(msg: Int) {
        val intent = Intent("updateIntentKey")
        intent.putExtra("IntentService", msg)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }
}
