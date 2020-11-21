package com.example.pizzaorderingapp
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Pizza app splash screen
* */
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, UserLoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 2500)
    }
}