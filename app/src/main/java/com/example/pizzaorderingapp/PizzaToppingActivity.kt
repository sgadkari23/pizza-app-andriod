package com.example.pizzaorderingapp
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Pizza tooping selection
* */
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity

class PizzaToppingActivity : AppCompatActivity() {
    var order:Order? = null

    lateinit var checkBoxCheese: CheckBox
    lateinit var checkBoxGreenPepper: CheckBox
    lateinit var checkBoxSmokedHam: CheckBox
    lateinit var checkBoxSpinach: CheckBox
    lateinit var checkBoxBlackOlives: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_topping)
        // extract intent data
        order = intent.extras?.get("pizzaOrder") as Order
        // initialize checkbox
        checkBoxCheese = findViewById(R.id.checkBoxCheese)
        checkBoxGreenPepper = findViewById(R.id.checkBoxGreenPepper)
        checkBoxSmokedHam = findViewById(R.id.checkBoxSmokedHam)
        checkBoxSpinach = findViewById(R.id.checkBoxSpinach)
        checkBoxBlackOlives = findViewById(R.id.checkBoxBlackOlives)
    }

    // Method to handle next button on click
    fun handleOnCheckoutButtonClick(v: View) {
        // Create toppings string using stringbuilder
        val toppings = StringBuilder()

        if (checkBoxCheese.isChecked) {
            toppings.append(getString(R.string.toppings_cheese) +"\n")
            order!!.cost = order!!.cost?.plus(1)
        }
        if (checkBoxGreenPepper.isChecked) {
            toppings.append(getString(R.string.toppings_greenpepper)+"\n")
        }
        if (checkBoxSmokedHam.isChecked) {
            toppings.append(getString(R.string.toppings_smokedham)+"\n")
            order!!.cost = order!!.cost?.plus(4)
        }
        if (checkBoxSpinach.isChecked) {
            toppings.append(getString(R.string.toppings_spinach)+"\n")
        }
        if (checkBoxBlackOlives.isChecked) {
            toppings.append(getString(R.string.toppings_blackolives)+"\n")
        }

        order?.toppings = toppings.toString()
        Log.i("order?.toppings ","order?.toppings "+order?.toppings)

        Log.i("Cost "," Total order "+order!!.cost)
        // Call customer details activity
        val intent = Intent(this@PizzaToppingActivity, CustomerDetails::class.java)
        intent.putExtra("pizzaOrder", order)
        startActivity(intent)
    }
}