package com.example.pizzaorderingapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pizzaorderingapp.model.OrderEntity
import com.example.pizzaorderingapp.viewmodel.OrderViewModel

class AdminHomeActivity : AppCompatActivity() {

    lateinit var orderViewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)

        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        orderViewModel.getAllOrders(this@AdminHomeActivity)?.observe(this, Observer {

            if (it == null) {
                Toast.makeText(this, "Cannot find all orders!", Toast.LENGTH_LONG).show()
            }
            else {
                for (order: OrderEntity in it)
                {
                    println("Order Status: " + order.status + " Username: " + order.userName)
                }
            }
        })
    }
}