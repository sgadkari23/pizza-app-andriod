package com.example.pizzaorderingapp
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Oder Class
* */
import java.io.Serializable

class Order : Serializable {
    var pizzaType: String?= null
    var pizzaSize: String? = null
    var toppings: String? =null
    var cost:Int? = 0
}