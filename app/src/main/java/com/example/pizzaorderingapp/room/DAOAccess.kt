package com.example.pizzaorderingapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pizzaorderingapp.Order
import com.example.pizzaorderingapp.model.OrderEntity
import com.example.pizzaorderingapp.model.UserEntity

// DAO for querying the database entity
@Dao
interface DAOAccess {
    // insert user query
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    // get user query
    @Query("SELECT * FROM user WHERE userName =:userName")
    fun getUser(userName: String?): LiveData<UserEntity>

    // insert order query
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(userEntity: OrderEntity)

    // get all orders query
    @Query("SELECT * FROM `order`")
    fun getAllOrders(): LiveData<List<OrderEntity>>

    // update user entity by primary key
    @Update
    fun updateUser(userEntity: UserEntity)

    // update order entity by primary key
    @Update
    fun updateOrder(orderEntity: OrderEntity)
}