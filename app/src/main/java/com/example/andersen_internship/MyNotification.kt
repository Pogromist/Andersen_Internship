package com.example.andersen_internship

import android.app.*
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity


class MyNotification private constructor() {

    private lateinit var context: Context

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun notification(fragmentActivity: FragmentActivity) {

        val notificationManager =
            fragmentActivity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val androidChannel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )

        androidChannel.enableLights(true)
        androidChannel.enableVibration(true)
        notificationManager.createNotificationChannel(androidChannel)

        var notification =
            Notification.Builder(fragmentActivity, CHANNEL_ID)
                .setContentTitle(CONTENT_TITLE)
                .setContentText(CONTENT_TEXT)
                .setSmallIcon(R.mipmap.ic_launcher)
        notificationManager.notify(0, notification.build())
    }

    companion object {

        const val CHANNEL_ID = "com.example.andersen_internship"
        const val CHANNEL_NAME = "Notification Channel"
        const val CONTENT_TITLE = "Notification"
        const val CONTENT_TEXT = "Sample Text"

        @get:Synchronized
        val instance = MyNotification()
        fun get(): Context {
            return instance.context
        }
    }

}
