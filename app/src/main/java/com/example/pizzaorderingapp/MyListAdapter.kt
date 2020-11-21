package com.example.pizzaorderingapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.pizzaorderingapp.model.OrderEntity
import com.example.pizzaorderingapp.viewmodel.OrderViewModel

class MyListAdapter(val context: Activity,  val orders:List<OrderEntity>, val orderViewModel:OrderViewModel) : BaseAdapter() {


    override fun getCount(): Int {

        return orders.size
    }

    override fun getItem(position: Int): OrderEntity {
        return orders[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.custom_list, parent, false)
        val titleText = convertView.findViewById(R.id.customerFirstNameTextview) as TextView
        val orderStatusSwitch = convertView.findViewById<Switch>(R.id.pizzaStatusSwitch)
        val statusTextView = convertView.findViewById<TextView>(R.id.pizzaStatusTextView)
        val orderID = convertView.findViewById<TextView>(R.id.orderIdIextView)
        val orderadd = convertView.findViewById<TextView>(R.id.orderAddressTextView)

        val order = getItem(position)

        statusTextView.setText(order.status)
        titleText.setText(order.fullName)
        orderID.text = order.orderId.toString()
        //+", "+order.address+", "+order.orderDate
        orderadd.text = order.address
        //        println("order status "+order.status)
        if(order.status ==  "In Progress"){
            orderStatusSwitch.setChecked(true)
        }else
        {
            orderStatusSwitch.isChecked = false
        }

        orderStatusSwitch?.setOnCheckedChangeListener({ _ , isChecked ->
            statusTextView.text = if (isChecked) "In Progress" else "Delivered"
            order.status = if (isChecked) "In Progress" else "Delivered"
            orderViewModel.updateOrder(
                context = context,
                order
            )
        })
        return convertView
    }

}