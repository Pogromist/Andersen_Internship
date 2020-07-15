package com.example.andersen_internship

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity

class MyNotification private constructor() {
    private lateinit var context: Context
    fun init(context: Context) {
        if (context == null) {
            this.context = context
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun notification(fragmentActivity: FragmentActivity) {
        val notif =
            fragmentActivity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val androidChannel = NotificationChannel(
            ANDROID_CHANNEL_ID,
            "Notification Channel", NotificationManager.IMPORTANCE_DEFAULT
        )
        androidChannel.enableLights(true)
        androidChannel.enableVibration(true)
        notif.createNotificationChannel(androidChannel)
        var notify: Notification? = null
        notify =
            Notification.Builder(fragmentActivity, ANDROID_CHANNEL_ID).setContentTitle("Notification")
                .setContentText("Sample Text").setContentTitle("Sample Subject")
                .setSmallIcon(R.mipmap.ic_launcher).build()
        notif.notify(0, notify)
    }

    companion object {
        val ANDROID_CHANNEL_ID = "com.example.andersen_internship"
        @get:Synchronized
        val instance = MyNotification()
        fun get(): Context {
            return instance.context
        }
    }
}
