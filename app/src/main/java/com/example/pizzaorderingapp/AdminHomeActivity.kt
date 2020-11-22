package com.example.pizzaorderingapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pizzaorderingapp.model.OrderEntity
import com.example.pizzaorderingapp.viewmodel.OrderViewModel

class AdminHomeActivity : AppCompatActivity() {

    // Intitalizing variables to hold order list and database model
    lateinit var orderViewModel: OrderViewModel
    lateinit var listView: ListView
    lateinit var allOrders:List<OrderEntity>

    // oncreate life cycle function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)

        // initialize order view model to interact with order entity
        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        // get all orders from the database
        orderViewModel.getAllOrders(this@AdminHomeActivity)?.observe(this, Observer {

            // error finding orders
            if (it == null) {
                Toast.makeText(this, "Cannot find all orders!", Toast.LENGTH_LONG).show()
            }
            else {
                allOrders = it

                // pass the orders list to custom list view adaptor
                listView = findViewById(R.id.listView)
                val myListAdapter = MyListAdapter(this, allOrders, orderViewModel)
                listView.adapter = myListAdapter
            }
        })

    }
}
