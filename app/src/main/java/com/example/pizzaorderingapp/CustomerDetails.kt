package com.example.pizzaorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.ArrayAdapter.createFromResource
import java.io.Serializable

class PersonalInformation: Serializable {
    var fullName: String?= null
    var mobileNo: Int? = null
    var postalCode: Int? =null
    var cardDetail: Int? =null
    var address: String? =null
    var cardType: String? =null
}


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
                        Toast.makeText(this@CustomerDetails,
                            "Cardtype " +
                                    "" + paymentcardtype[position], Toast.LENGTH_SHORT).show()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // write code to perform some action
                    }
                }
            }


    }

    fun onclickOnCustomerdetailsButtonClick(v: View) {
        println("onclick function clicked")
        if (v.id == R.id.buttonNext) {

            personalInformation.fullName = findViewById<TextView>(R.id.editTextTextPersonName).text.toString()
            //personalInformation.mobileNo = findViewById<TextView>(R.id.editTextPhone).toString()
            personalInformation.address = findViewById<TextView>(R.id.editTextTextMultiLine).text.toString()
            println("Full Name "+personalInformation.fullName)
            println("Address "+personalInformation.address)

            val intent = Intent(this@CustomerDetails, CheckoutActivity::class.java)
            intent.putExtra("pizzaOrder", order)
            intent.putExtra("customerInfo", personalInformation)
            startActivity(intent)
        }
    }
}