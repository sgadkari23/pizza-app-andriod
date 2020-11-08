package com.example.pizzaorderingapp
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Display name of cities
* */

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_pizza_store_cities.*


class PizzaStoreCitiesActivity : AppCompatActivity() {

    lateinit var citySelected : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_store_cities)
        //print(R.id.storeCitiesListView)
        var cityArray = resources.getStringArray(R.array.cities_name)
        var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, cityArray )
        storeCitiesListView.adapter = arrayAdapter
        storeCitiesListView.setOnItemClickListener { parent, View, position, id ->
            citySelected = cityArray[position]
            val intent = Intent(this@PizzaStoreCitiesActivity, PizzaStoresMapActivity::class.java)
            intent.putExtra("storeCity", citySelected)
            startActivity(intent)
        }
    }

}