package com.example.pizzaorderingapp
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Pizza size selection screen
* */
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_pizza_size.*

class PizzaSizeActivity : AppCompatActivity() {
    var order:Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_size)

        // Extract intent data passed from previous activity
        order = intent.extras?.get("pizzaOrder") as Order

        // Populate pizza size
        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
            })
    }

    // Method to handle next button on click
    fun handleOnChooseToppingButtonClick(v: View) {
        if (v.id == R.id.btnChooseToppings) {
            // Call pizza toppings activity with order object
            val intent = Intent(this@PizzaSizeActivity, PizzaToppingActivity::class.java)
            val radio: RadioButton = findViewById(radioGroup.checkedRadioButtonId)
            order?.pizzaSize = radio.text.toString()
            when(order?.pizzaSize){
                "Small ($14)" -> order!!.cost = 14
                "Medium ($18)" -> order!!.cost =18
                "Large ($20)" -> order!!.cost =  20
                "Extra-Large ($24)"-> order!!.cost = 24
            }
            intent.putExtra("pizzaOrder", order)
            startActivity(intent)
        }
    }
}