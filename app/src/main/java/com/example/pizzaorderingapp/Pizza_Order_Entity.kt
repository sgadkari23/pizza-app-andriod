package com.example.pizzaorderingapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pizza_Order_Entity (
    @PrimaryKey
    val orderID: Int = 0,
    var type: String="",
    var size: String="",
    var toppings: String=""
)