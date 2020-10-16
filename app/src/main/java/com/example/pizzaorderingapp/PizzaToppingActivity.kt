package com.example.pizzaorderingapp

import android.content.Intent
import android.os.Bundle
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
    val toppings = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_topping)
        order = intent.extras?.get("pizzaOrder") as Order
        checkBoxCheese = findViewById(R.id.checkBoxCheese)
        checkBoxGreenPepper = findViewById(R.id.checkBoxGreenPepper)
        checkBoxSmokedHam = findViewById(R.id.checkBoxSmokedHam)
        checkBoxSpinach = findViewById(R.id.checkBoxSpinach)
        checkBoxBlackOlives = findViewById(R.id.checkBoxBlackOlives)
    }

    // Method to handle show button on click
    fun handleOnCheckoutButtonClick(v: View) {
        if (checkBoxCheese.isChecked)
            toppings.append("\n" + getString(R.string.toppings_cheese))
        if (checkBoxGreenPepper.isChecked)
            toppings.append("\n" + getString(R.string.toppings_greenpepper))
        if (checkBoxSmokedHam.isChecked)
            toppings.append("\n" + getString(R.string.toppings_smokedham))
        if (checkBoxSpinach.isChecked)
            toppings.append("\n" + getString(R.string.toppings_spinach))
        if (checkBoxBlackOlives.isChecked)
            toppings.append("\n" + getString(R.string.toppings_blackolives))

        order?.toppings = toppings.toString()
        intent.putExtra("pizzaOrder", order)
        val intent = Intent(this@PizzaToppingActivity, CheckoutActivity::class.java)
        startActivity(intent)
    }
}