package com.example.pizzaorderingapp
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Checkout pizza order
* */
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.pizzaorderingapp.viewmodel.OrderViewModel
import com.example.pizzaorderingapp.viewmodel.UserViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class CheckoutActivity : AppCompatActivity() {
    var order:Order? = null
    var personalInformation:PersonalInformation? = null

    // initialize order view model
    lateinit var orderViewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        // Extract order and personal information data from intent object
        order = intent.extras?.get("pizzaOrder") as Order
        personalInformation = intent.extras?.get("customerInfo") as PersonalInformation

        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

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
    @RequiresApi(Build.VERSION_CODES.O)
    fun handleOnConfirmButtonClick(v: View) {
        if (v.id == R.id.btnConfirmation) {

            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val timeStamp = current.format(formatter)

            // insert new order to database
            orderViewModel.insertOrder(context = this@CheckoutActivity,
                fullName = personalInformation?.fullName!!,
                address = personalInformation?.address!!,
                mobileNo = personalInformation?.mobileNo!!,
                postalCode = personalInformation?.postalCode!!,
                cardNumber = personalInformation?.cardNumber!!,
                cardExpiry = personalInformation?.cardExpiry!!,
                userName = personalInformation?.userName!!,
                status = "In Progress",
                orderDate = timeStamp
            )

            Toast.makeText(this, "Thank you for ordering. Your order has been placed", Toast.LENGTH_LONG).show()
            val intent = Intent(this@CheckoutActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}