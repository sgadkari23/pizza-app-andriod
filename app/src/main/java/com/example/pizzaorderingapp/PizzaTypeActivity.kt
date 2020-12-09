package com.example.pizzaorderingapp
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Select pizza type activity
* * */

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_pizza_size.*
import kotlinx.android.synthetic.main.activity_pizza_store_cities.*

class PizzaTypeActivity : AppCompatActivity() {
    var order = Order()
    lateinit var radioGroup:RadioGroup
    //var order:Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_type)
        radioGroup = findViewById(R.id.pizzaTypeRadioGroup)

        // Populate pizza type
        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
            })
    }

    fun handleOnChoosePizzaTypeButtonClick(v: View) {
        if (v.id == R.id.pizzaTypeButton) {
            // Call pizza toppings activity with order object
            val intent = Intent(this@PizzaTypeActivity, PizzaSizeActivity::class.java)
            val radio: RadioButton = findViewById(radioGroup.checkedRadioButtonId)
            order.pizzaType = radio.text.toString()
            intent.putExtra("pizzaOrder", order)
            startActivity(intent)
        }
    }
}