package com.example.pizzaorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_pizza_size.*

class PizzaSizeActivity : AppCompatActivity() {
    var order:Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_size)

        order = intent.extras?.get("pizzaOrder") as Order
        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
            })

    }

    // Method to handle show button on click
    fun handleOnChooseToppingButtonClick(v: View) {

        if (v.id == R.id.btnChooseToppings) {


            val intent = Intent(this@PizzaSizeActivity, CheckoutActivity::class.java)
            val radio: RadioButton = findViewById(radioGroup.checkedRadioButtonId)
            order?.pizzaSize = radio.text.toString()
            intent.putExtra("pizzaOrder", order)
            Toast.makeText(applicationContext,"On click : ${radio.text}",Toast.LENGTH_SHORT).show()
            startActivity(intent)


        }
    }

}