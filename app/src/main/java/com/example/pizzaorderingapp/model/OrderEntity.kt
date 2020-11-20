package com.example.pizzaorderingapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "order")
data class OrderEntity (
    @ColumnInfo(name = "fullName")var fullName: String,
    @ColumnInfo(name = "address")var address: String,
    @ColumnInfo(name = "mobileNo")var mobileNo: String,
    @ColumnInfo(name = "postalCode")var postalCode: String,
    @ColumnInfo(name = "cardNumber")var cardNumber: String,
    @ColumnInfo(name = "cardExpiry")var cardExpiry: String,
    @ColumnInfo(name = "userName")var userName: String,
    @ColumnInfo(name = "orderDate")var orderDate: String,
    @ColumnInfo(name = "status")var status: String,
    ){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "orderId")
    var orderId: Int? = null
}