package com.example.pizzaorderingapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.pizzaorderingapp.model.OrderEntity
import com.example.pizzaorderingapp.viewmodel.OrderViewModel
import kotlinx.android.synthetic.main.activity_admin_home.*

class GetAllOrdersOfCustomerActivity : AppCompatActivity() {

    lateinit var orderViewModel: OrderViewModel
    lateinit var userName: String
    lateinit var listView: ListView
    lateinit var allOrders:List<OrderEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_all_orders_of_customer)

        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        val sharedPref = this@GetAllOrdersOfCustomerActivity?.getSharedPreferences("LoggedInUser", Context.MODE_PRIVATE)
        userName = sharedPref.getString("userName", "")!!
        // get all orders from the database
        orderViewModel.getAllOrderOfCustomer(this@GetAllOrdersOfCustomerActivity, userName)!!.observe(this, {

            // error finding orders
            if (it == null) {
                Toast.makeText(this, "Cannot find all orders!", Toast.LENGTH_LONG).show()
            }
            else {
                allOrders = it
                Log.i("database","count :"+ allOrders.size.toString())
                // pass the orders list to custom list view adaptor
                listView = findViewById(R.id.customerListView)
                val myListAdapter = CustomerOrderListAdapter(this, allOrders)
                listView.adapter = myListAdapter
            }
        })
    }
}