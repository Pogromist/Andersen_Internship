package com.example.andersen_internship

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

class MyNotification() : Application() {

    val CHANNEL_ID = "com.example.andersen_internship.channel1"

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel(CHANNEL_ID, "My channel", "Description of my channel")
    }

    private fun createNotificationChannel(id: String, name: String, channelDescription: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance).apply {
                description = channelDescription
            }
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun displayNotification(context: Context, notificationManager: NotificationManager) {
        val notificationId = 1
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Notification")
            .setContentText("Description of notification")
            .setSmallIcon(R.drawable.ic_favorite_black)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        notificationManager.notify(notificationId, notification)
    }
}