package com.example.pizzaorderingapp
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Play music in background- Service
* */

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
// android services for background music
public class AndroidBackgroundServices: Service() {
    private val TAG = "MusicService"
    lateinit var mediaPlayer:MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    override fun onCreate() {
        mediaPlayer = MediaPlayer.create(this, R.raw.music1)
        mediaPlayer.isLooping = true
        Log.i(TAG, "Service onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "Service onStartCommand $startId")
        mediaPlayer.start()
        return Service.START_STICKY
    }

    override fun onDestroy() {
        Log.i(TAG, "Service onDestroy")
        mediaPlayer.stop()
    }
}