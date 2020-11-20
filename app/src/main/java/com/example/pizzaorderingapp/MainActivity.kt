package com.example.pizzaorderingapp
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Home screen of Pizza app
* */
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pizzaorderingapp.model.OrderEntity
import com.example.pizzaorderingapp.viewmodel.OrderViewModel

class MainActivity : AppCompatActivity() {

    lateinit var orderViewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        val clickableText = findViewById(R.id.pizzaStoresTextView) as TextView
           clickableText.setOnClickListener {
            val intent = Intent(this@MainActivity, PizzaStoreCitiesActivity::class.java)
            startActivity(intent)
        }

        orderViewModel.getAllOrders(this@MainActivity)?.observe(this, Observer {

            if (it == null) {
                Toast.makeText(this, "Cannot find all orders!", Toast.LENGTH_LONG).show()
            }
            else {
                for (order:OrderEntity in it)
                {
                    println("Order Status: " + order.status + " Username: " + order.userName)
                }
            }
        })
    }

    // Populate context menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.pizza_types,menu)
        return true
    }

    // Handler when user selects a pizza from context menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Create new object of type Order
        val order = Order()

        // Set appropriate pizza type in order object based on user selection
        when(item.itemId){
            R.id.meatSupreme ->{
                order.pizzaType = getString(R.string.meat_supreme)
            }
            R.id.superHawaiian ->{
                order.pizzaType = getString(R.string.super_hawaiian)
            }
            R.id.veggie ->{
                order.pizzaType = getString(R.string.veggie)
            }
            R.id.mediterranean ->{
                order.pizzaType = getString(R.string.mediterranean)
                }
            R.id.cheddarSupreme -> {
                order.pizzaType = getString(R.string.cheddar_supreme)
            }
        }

        // Call pizza size activity and pass order object through intent
        val intent = Intent(this@MainActivity, PizzaSizeActivity::class.java)
        intent.putExtra("pizzaOrder", order)
        startActivity(intent)
        return true
    }
}