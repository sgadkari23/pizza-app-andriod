package com.example.pizzaorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class PizzaSizeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_size)
    }

    // Method to handle show button on click
    fun handleOnChooseToppingButtonClick(v: View) {
        if (v.id == R.id.btnChooseToppings) {

            val intent = Intent(this@PizzaSizeActivity, PizzaToppingActivity::class.java)
            startActivity(intent)


        }
    }

}