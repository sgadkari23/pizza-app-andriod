package com.example.pizzaorderingapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CustomerDetails : AppCompatActivity() {
    var order:Order? = null
    val personalInformation = PersonalInformation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_details)

        order = intent.extras?.get("pizzaOrder") as Order

        val paymentcardtype = resources.getStringArray(R.array.payment_cardtype)
        val spinnerCardtype = findViewById<Spinner>(R.id.spinnerCardtype)

        if (spinnerCardtype != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, paymentcardtype)
            spinnerCardtype.adapter = adapter
            spinnerCardtype.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    personalInformation.cardType = paymentcardtype[position]
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }
    }

    fun onclickOnCustomerdetailsButtonClick(v: View) {
        println("onclick function clicked")
        if (v.id == R.id.buttonNext) {
            personalInformation.fullName = findViewById<TextView>(R.id.editTextTextPersonName).text.toString()
            personalInformation.address = findViewById<TextView>(R.id.editTextTextAddress).text.toString()
            val intent = Intent(this@CustomerDetails, CheckoutActivity::class.java)
            intent.putExtra("pizzaOrder", order)
            intent.putExtra("customerInfo", personalInformation)
            startActivity(intent)
        }
    }
}