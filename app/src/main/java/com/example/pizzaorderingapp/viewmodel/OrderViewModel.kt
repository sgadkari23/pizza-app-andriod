package com.example.pizzaorderingapp.viewmodel
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: order table's viewmodel
* */
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pizzaorderingapp.model.OrderEntity
import com.example.pizzaorderingapp.repository.AppRepository

// Interface layer between the database repository and UI activity
class OrderViewModel: ViewModel() {
    // order entity list
    var orderEntity: LiveData<List<OrderEntity>>? = null

    // insert new order
    fun insertOrder(context: Context, fullName: String, address: String, mobileNo: String,  postalCode: String,  cardNumber: String,  cardExpiry: String,  userName: String,  status: String,  orderDate: String) {
        AppRepository.insertOrder(context, fullName = fullName, address = address, mobileNo = mobileNo, postalCode = postalCode, cardNumber = cardNumber, cardExpiry = cardExpiry, userName = userName, status = status, orderDate = orderDate)
    }

    // get all orders
    fun getAllOrders(context: Context) : LiveData<List<OrderEntity>>? {
        orderEntity = AppRepository.getAllOrders(context)
        return orderEntity
    }

    // update order information
    fun updateOrder(context: Context, orderEntity: OrderEntity){
        AppRepository.updateOrder(context,orderEntity)
    }

}