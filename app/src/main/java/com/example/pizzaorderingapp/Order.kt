package com.example.pizzaorderingapp

import java.io.Serializable

class Order : Serializable {
    var pizzaType: String?= null
    var pizzaSize: String? = null
    var toppings: String? =null
}