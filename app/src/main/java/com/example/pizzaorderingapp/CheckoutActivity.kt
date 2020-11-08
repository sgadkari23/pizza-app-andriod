package com.example.pizzaorderingapp
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Checkout pizza order
* */
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

        // Extract order and personal information data from intent object
        order = intent.extras?.get("pizzaOrder") as Order
        personalInformation = intent.extras?.get("customerInfo") as PersonalInformation

        // Display name, address, type, size and toppings on screen
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
            Toast.makeText(this, "Thank you for ordering. Your order has been placed", Toast.LENGTH_LONG).show()
            val intent = Intent(this@CheckoutActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}