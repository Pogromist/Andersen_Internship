package com.example.andersen_internship

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_MIN
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.andersen_internship.ProgressBarFragment.Companion.INTENT_KEY
import java.lang.Thread.sleep


class MyService() : Service() {

    companion object {
        const val CHANNEL_ID = "my_service"
        const val CHANNEL_NAME = "My Background Service"
        const val INTENT_NAME = "data"
        const val INTENT_VALUE = "fromoutside"
        const val CONTENT_TITLE = "Service notification"
        const val CONTENT_TEXT_RUNNING = "Running"
        const val CONTENT_TEXT_FINISH = "Finished"
        const val PROGRESS_MAX = 100
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread(Runnable {
            loading()
        }).start()

        return START_NOT_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loading() {
        val channelId = createNotificationChannel(CHANNEL_ID, CHANNEL_NAME)

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent.putExtra(INTENT_NAME, INTENT_VALUE)
        val contentIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)

        var notification = notificationBuilder.setOngoing(true)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(PRIORITY_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .setContentTitle(CONTENT_TITLE)
            .setContentText(CONTENT_TEXT_RUNNING)
            .setProgress(PROGRESS_MAX, 0, false)
            .setContentIntent(contentIntent)

        for (i in 0..100) {
            sleep(50)
            notification.setProgress(PROGRESS_MAX, i, false)
            startForeground(1, notification.build())
            sendMessageToActivity(i)
        }
        stopForeground(true)
        stopSelf()
        showNotification()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun showNotification() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent.putExtra(INTENT_NAME, INTENT_VALUE)
        val contentIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        var notification =
            Notification.Builder(this, MyNotification.CHANNEL_ID)
                .setContentTitle(CONTENT_TITLE)
                .setContentText(CONTENT_TEXT_FINISH)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(contentIntent)
        notificationManager.notify(0, notification.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val chan = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_NONE
        )
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }

    private fun sendMessageToActivity(msg: Int) {
        val intent = Intent(INTENT_KEY)
        intent.putExtra("key", msg)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }
}
