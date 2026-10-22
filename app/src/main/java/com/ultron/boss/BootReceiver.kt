package com.ultron.boss

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            // BOSS er phone on holei ULTRON auto start hobe
            val serviceIntent = Intent(context, UltronService::class.java)
            context.startForegroundService(serviceIntent)
        }
    }
}
