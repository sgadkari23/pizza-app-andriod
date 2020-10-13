package com.example.pizzaorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class PizzaToppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_topping)
    }

    // Method to handle show button on click
    fun handleOnCheckoutButtonClick(v: View) {
        if (v.id == R.id.btnCheckout) {

            val intent = Intent(this@PizzaToppingActivity, CheckoutActivity::class.java)
            startActivity(intent)


        }
    }
}