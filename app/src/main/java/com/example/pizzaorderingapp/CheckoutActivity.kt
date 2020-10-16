package com.example.pizzaorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class CheckoutActivity : AppCompatActivity() {
    var order:Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        order = intent.extras?.get("pizzaOrder") as Order

        val textView = findViewById<TextView>(R.id.textViewPizzaType)
        textView.text = order?.pizzaType

        val textView1 = findViewById<TextView>(R.id.textViewPizzaSize)
        textView1.text = order?.pizzaSize

        val textViewToppings = findViewById<TextView>(R.id.textViewPizzaToppings)
        textViewToppings.text = order?.toppings
    }

    // Method to handle show button on click
    fun handleOnConfirmButtonClick(v: View) {
        if (v.id == R.id.btnConfirmation) {

            //val intent = Intent(this@CheckoutActivity, OrderDetailActivity::class.java)
            val intent = Intent(this@CheckoutActivity, MainActivity::class.java)
            startActivity(intent)


        }
    }
}