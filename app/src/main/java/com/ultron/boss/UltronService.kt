package com.ultron.boss

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class UltronService : Service() {

    override fun onCreate() {
        super.onCreate()
        createChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification: Notification = NotificationCompat.Builder(this, "ULTRON_BOSS_CHANNEL")
            .setContentTitle("ULTRON BOSS V1 - 165 Brains Active")
            .setContentText("Screen OFF teo sunbo BOSS - Ami Tomar BOSS")
            .setSmallIcon(android.R.drawable.ic_btn_speak_now)
            .setOngoing(true)
            .build()

        startForeground(1, notification)

        // 24H VOICE LISTENING + 100 TRADING BRAINS BACKGROUND
        return START_STICKY
    }

    private fun createChannel() {
        val channel = NotificationChannel(
            "ULTRON_BOSS_CHANNEL",
            "ULTRON BOSS Service",
            NotificationManager.IMPORTANCE_LOW
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
