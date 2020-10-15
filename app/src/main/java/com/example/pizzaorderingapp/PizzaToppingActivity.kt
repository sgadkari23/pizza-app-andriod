package com.example.pizzaorderingapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzaorderingapp.R.id.checkBox

class PizzaToppingActivity : AppCompatActivity() {
    var order:Order? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_topping)
        order = intent.extras?.get("pizzaOrder") as Order

        val Cheese = (CheckBox)findViewById(checkBox);
        val GreenPepper = (CheckBox)findViewById(R.id.checkBox2);
        val SmokedHam = (CheckBox)findViewById(R.id.checkBox3);
        val Spinach = (CheckBox)findViewById(R.id.checkBox4);
        //Button btn = (Button)findViewById(R.id.getBtn);
    }

    // Method to handle show button on click
    fun handleOnCheckoutButtonClick(v: View) {
        if (v.id == R.id.btnCheckout) {

            val intent = Intent(this@PizzaToppingActivity, CheckoutActivity::class.java)
            startActivity(intent)


        }
    }
}