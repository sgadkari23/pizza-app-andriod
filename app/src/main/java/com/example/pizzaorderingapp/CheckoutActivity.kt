package com.example.pizzaorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
    }

    // Method to handle show button on click
    fun handleOnConfirmButtonClick(v: View) {
        if (v.id == R.id.btnConfirmation) {

            val intent = Intent(this@CheckoutActivity, OrderDetailActivity::class.java)
            startActivity(intent)


        }
    }
}