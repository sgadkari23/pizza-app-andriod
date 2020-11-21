package com.example.pizzaorderingapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pizzaorderingapp.Order
import com.example.pizzaorderingapp.model.OrderEntity
import com.example.pizzaorderingapp.model.UserEntity

@Dao
interface DAOAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM user WHERE userName =:userName")
    fun getUser(userName: String?): LiveData<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(userEntity: OrderEntity)

    @Query("SELECT * FROM `order`")
    fun getAllOrders(): LiveData<List<OrderEntity>>

    @Update
    fun updateUser(userEntity: UserEntity)

    //@Query("update user set firstName=:firstnm, lastName =:lastnm where userName= :usernm")
    //fun updateUser(firstnm: String, lastnm: String, usernm: String)
    //fun updateUser(userName:String, firstName:String,lastName: String)
}