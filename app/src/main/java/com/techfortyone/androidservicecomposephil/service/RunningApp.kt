package com.techfortyone.androidservicecomposephil.service

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class RunningApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "running_channel",
                "Running Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificatioManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificatioManager.createNotificationChannel(channel)
        }
    }
}