package com.example.pizzaorderingapp.repository
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Repository class
* */
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.pizzaorderingapp.model.OrderEntity
import com.example.pizzaorderingapp.model.UserEntity
import com.example.pizzaorderingapp.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*

// initialize database and is the single source of data for entire application
class AppRepository {

    companion object {
        var appDatabase: AppDatabase? = null
        var userEntity: LiveData<UserEntity>? = null
        var orderEntity: LiveData<List<OrderEntity>>? = null

        // initialize db instance
        fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        // update user
        fun updateUser(context: Context,userEntity: UserEntity){
            // get database instance
            appDatabase = initializeDB(context)
            // call dao in coroutine
            CoroutineScope(IO).launch {
                //val updateDetails = UserEntity(userName=userName,firstName=firstName,lastName=lastName)
                appDatabase!!.dao().updateUser(userEntity)
            }
        }

        // update order
        fun updateOrder(context: Context,orderEntity: OrderEntity){
            appDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                //val updateDetails = UserEntity(userName=userName,firstName=firstName,lastName=lastName)
                appDatabase!!.dao().updateOrder(orderEntity)
            }
        }

        // insert user
        fun insertData(context: Context, firstName: String, lastName: String, userName: String,  password: String,  roleType: String) {

            appDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val loginDetails = UserEntity(firstName = firstName, lastName = lastName, userName = userName, roleType = roleType, password = password)
                appDatabase!!.dao().insertUser(loginDetails)
            }

        }

        // get user by username
        fun getUserDetails(context: Context, emailId: String) : LiveData<UserEntity>? {

            appDatabase = initializeDB(context)
            userEntity = appDatabase!!.dao().getUser(emailId)
            return userEntity
        }

        // insert new order
        fun insertOrder(context: Context, fullName: String, address: String, mobileNo: String,  postalCode: String,  cardNumber: String,  cardExpiry: String,  userName: String,  status: String,  orderDate: String) {
            appDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val orderDetails = OrderEntity(fullName = fullName, address = address, mobileNo = mobileNo, postalCode = postalCode, cardNumber = cardNumber, cardExpiry = cardExpiry, userName = userName, status = status, orderDate = orderDate)
                appDatabase!!.dao().insertOrder(orderDetails)
            }
        }

        // get all orders
        fun getAllOrders(context: Context) : LiveData<List<OrderEntity>>? {
            appDatabase = initializeDB(context)
            orderEntity = appDatabase!!.dao().getAllOrders()
            return orderEntity
        }

    }
}