package com.example.pizzaorderingapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Switch
import android.widget.TextView
import com.example.pizzaorderingapp.model.OrderEntity
import com.example.pizzaorderingapp.viewmodel.OrderViewModel

class CustomerOrderListAdapter (val context: Activity, val orders:List<OrderEntity>) : BaseAdapter() {


    override fun getCount(): Int {

        return orders.size
    }

    override fun getItem(position: Int): OrderEntity {
        return orders[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // individual row of list
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.custom_customer_order_list, parent, false)

        val titleText = convertView.findViewById(R.id.customerOrderTextView) as TextView
        val orderAddress: TextView =  convertView.findViewById(R.id.orderAddressTextView) as TextView

        val order = getItem(position)

        titleText.setText(order.fullName)
        orderAddress.setText(order.address)
        return convertView
    }

}