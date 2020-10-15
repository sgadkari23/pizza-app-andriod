package com.example.pizzaorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        val order = intent.extras?.get("pizzaOrder") as Order

        val textView = findViewById<TextView>(R.id.textView8)
        textView.text = order.pizzaType

        val textView1 = findViewById<TextView>(R.id.textView9)
        textView1.text = order.pizzaSize


        // val intent = intent
       // val message = intent.getStringExtra("PizzaMenueType")
       /* val textView = findViewById<TextView>(R.id.textView8)

        textView.text = intent.getStringExtra("PizzaMenueType") --- supriya */

       /* val textView1 = findViewById<TextView>(R.id.textView9)
        textView.text = message

        val textView3 = findViewById<TextView>(R.id.textView8)
        textView.text = message*/

    }

    // Method to handle show button on click
    /*fun handleOnConfirmButtonClick(v: View) {
        if (v.id == R.id.btnConfirmation) {

            val intent = Intent(this@CheckoutActivity, OrderDetailActivity::class.java)
            startActivity(intent)


        }
    } */
}