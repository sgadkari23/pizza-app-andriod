package com.example.pizzaorderingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class OrderDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        // Define intent
        val intent = intent

        // Get user's detail from MainActivity using intent
        val name = intent.getStringExtra("MeatSupreme")


        // Get reference to text view and assign to a variable
        val outputView1 = findViewById<TextView>(R.id.outputTextView1)

        // Set text to text view
        outputView1.text = name
    }

}