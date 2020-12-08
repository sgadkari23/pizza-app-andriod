package com.example.pizzaorderingapp

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import com.example.pizzaorderingapp.model.OrderEntity
import com.example.pizzaorderingapp.viewmodel.OrderViewModel

class CustomerOrderListAdapter (val context: Activity, val orders:List<OrderEntity>) : BaseAdapter() {

    private val TAG:String = "OrderListAdaptor"
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
        val orderStatusAddress: TextView =  convertView.findViewById(R.id.customerOrderStatusTextView) as TextView
        val order = getItem(position)
        val trackButtonVisibility = convertView.findViewById(R.id.trackButton) as Button

        if(order.status == "In Progress"){
            trackButtonVisibility.visibility = View.VISIBLE
        }
        trackButtonVisibility.setOnClickListener(View.OnClickListener {
          //  Log.i(TAG,"onClick")
            val intent = Intent(context, CustomerOrderTrackingActivity::class.java)
            context.startActivity(intent)
        })
        titleText.setText(order.fullName)
        orderAddress.setText(order.address)
        orderStatusAddress.setText(order.status)
        return convertView
    }

}
