package com.example.pizzaorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class CheckoutActivity : AppCompatActivity() {
    var order:Order? = null
    var personalInformation:PersonalInformation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        order = intent.extras?.get("pizzaOrder") as Order
        personalInformation = intent.extras?.get("customerInfo") as PersonalInformation

        val customerfullName = findViewById<TextView>(R.id.textViewCustomerName)
        customerfullName.text = personalInformation?.fullName

        val customerAddress = findViewById<TextView>(R.id.textViewCustomerAddress)
        customerAddress.text = personalInformation?.address

        val pizzaType = findViewById<TextView>(R.id.textViewPizzaType)
        pizzaType.text = order?.pizzaType

        val pizzaSize = findViewById<TextView>(R.id.textViewPizzaSize)
        pizzaSize.text = order?.pizzaSize

        val pizzaToppings = findViewById<TextView>(R.id.textViewPizzaToppings)
        pizzaToppings.text = order?.toppings
    }

    // Method to handle show button on click
    fun handleOnConfirmButtonClick(v: View) {
        if (v.id == R.id.btnConfirmation) {
            Toast.makeText(this, "Your has been placed", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@CheckoutActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}