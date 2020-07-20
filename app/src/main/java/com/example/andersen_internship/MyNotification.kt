package com.example.andersen_internship

import android.app.*
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

        val notificationManager =
            fragmentActivity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val androidChannel = NotificationChannel(
            CHANNEL_ID,
            "Notification Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        androidChannel.enableLights(true)
        androidChannel.enableVibration(true)
        notificationManager.createNotificationChannel(androidChannel)

        var notification =
            Notification.Builder(fragmentActivity, CHANNEL_ID)
                .setContentTitle("Notification")
                .setContentText("Sample Text")
                .setSmallIcon(R.mipmap.ic_launcher)
        notificationManager.notify(0, notification.build())
    }

    companion object {

        val CHANNEL_ID = "com.example.andersen_internship"

        @get:Synchronized
        val instance = MyNotification()
        fun get(): Context {
            return instance.context
        }
    }

}
