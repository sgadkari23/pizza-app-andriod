package com.example.pizzaorderingapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pizzaorderingapp.model.OrderEntity
import com.example.pizzaorderingapp.model.UserEntity
import com.example.pizzaorderingapp.repository.AppRepository
import java.util.*

class OrderViewModel: ViewModel() {

    var orderEntity: LiveData<List<OrderEntity>>? = null

    fun insertOrder(context: Context, fullName: String, address: String, mobileNo: String,  postalCode: String,  cardNumber: String,  cardExpiry: String,  userName: String,  status: String,  orderDate: String) {
        AppRepository.insertOrder(context, fullName = fullName, address = address, mobileNo = mobileNo, postalCode = postalCode, cardNumber = cardNumber, cardExpiry = cardExpiry, userName = userName, status = status, orderDate = orderDate)
    }

    fun getAllOrders(context: Context) : LiveData<List<OrderEntity>>? {
        orderEntity = AppRepository.getAllOrders(context)
        return orderEntity
    }

}