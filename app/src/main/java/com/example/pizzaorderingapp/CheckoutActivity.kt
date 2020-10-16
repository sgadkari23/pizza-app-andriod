package com.example.pizzaorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class CheckoutActivity : AppCompatActivity() {
    var order:Order? = null
    var personalInformation:PersonalInformation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        order = intent.extras?.get("pizzaOrder") as Order
        personalInformation = intent.extras?.get("customerInfo") as PersonalInformation

        val fullName = findViewById<TextView>(R.id.textViewCustomerName)
        fullName.text = personalInformation?.fullName

        val address = findViewById<TextView>(R.id.textViewCustomerAddress)
        address.text = personalInformation?.address

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
           val intent = Intent(this@CheckoutActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}