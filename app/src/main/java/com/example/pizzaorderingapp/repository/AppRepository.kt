package com.example.pizzaorderingapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.pizzaorderingapp.model.OrderEntity
import com.example.pizzaorderingapp.model.UserEntity
import com.example.pizzaorderingapp.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*

class AppRepository {

    companion object {

        var appDatabase: AppDatabase? = null

        var userEntity: LiveData<UserEntity>? = null
        var orderEntity: LiveData<List<OrderEntity>>? = null

        fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, firstName: String, lastName: String, userName: String,  password: String,  roleType: String) {

            appDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val loginDetails = UserEntity(firstName = firstName, lastName = lastName, userName = userName, roleType = roleType, password = password)
                appDatabase!!.dao().insertUser(loginDetails)
            }

        }

        fun getUserDetails(context: Context, emailId: String) : LiveData<UserEntity>? {

            appDatabase = initializeDB(context)
            userEntity = appDatabase!!.dao().getUser(emailId)
            return userEntity
        }

        fun insertOrder(context: Context, fullName: String, address: String, mobileNo: String,  postalCode: String,  cardNumber: String,  cardExpiry: String,  userName: String,  status: String,  orderDate: String) {
            appDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val orderDetails = OrderEntity(fullName = fullName, address = address, mobileNo = mobileNo, postalCode = postalCode, cardNumber = cardNumber, cardExpiry = cardExpiry, userName = userName, status = status, orderDate = orderDate)
                appDatabase!!.dao().insertOrder(orderDetails)
            }
        }

        fun getAllOrders(context: Context) : LiveData<List<OrderEntity>>? {
            appDatabase = initializeDB(context)
            orderEntity = appDatabase!!.dao().getAllOrders()
            return orderEntity
        }

    }
}