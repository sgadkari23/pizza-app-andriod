package com.example.pizzaorderingapp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pizzaorderingapp.model.OrderEntity
import com.example.pizzaorderingapp.viewmodel.OrderViewModel

class AdminHomeActivity : AppCompatActivity() {

    lateinit var orderViewModel: OrderViewModel
    lateinit var listView: ListView
    lateinit var allOrders:List<OrderEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)

        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        orderViewModel.getAllOrders(this@AdminHomeActivity)?.observe(this, Observer {

            if (it == null) {
                Toast.makeText(this, "Cannot find all orders!", Toast.LENGTH_LONG).show()
            }
            else {
                allOrders = it

                listView = findViewById(R.id.listView)
                val myListAdapter = MyListAdapter(this, allOrders, orderViewModel)
                listView.adapter = myListAdapter
                listView.setOnItemClickListener(){adapterView, view, position, id ->
                    val itemAtPos = adapterView.getItemAtPosition(position)
                    val itemIdAtPos = adapterView.getItemIdAtPosition(position)
                    Toast.makeText(this, "Click on item at $itemAtPos its item id $itemIdAtPos", Toast.LENGTH_LONG).show()
                }
            }
        })

    }
}
