package com.example.pizzaorderingapp
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Home screen of Pizza app
* */
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    // intent call on store button click
    fun onStoreButtonClicked(v:View){
        val intent = Intent(this@MainActivity, PizzaStoreCitiesActivity::class.java)
        startActivity(intent)
    }
    // intent call on update button click
    fun onUpdateProfileButtonClicked(v:View){
        val intent = Intent(this@MainActivity, EditProfileActivity::class.java)
        startActivity(intent)
    }
    // User Logout
    fun onLogoutButtonPressed(v: View){
        if(v.id == R.id.logoutButton){
            val intent = Intent(this@MainActivity, UserLoginActivity::class.java)
            Toast.makeText(this, "Logout successfully!", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
    }

    // Populate context menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.pizza_types,menu)
        return true
    }

    // Handler when user selects a pizza from context menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Create new object of type Order
        val order = Order()

        // Set appropriate pizza type in order object based on user selection
        when(item.itemId){
            R.id.meatSupreme ->{
                order.pizzaType = getString(R.string.meat_supreme)
            }
            R.id.superHawaiian ->{
                order.pizzaType = getString(R.string.super_hawaiian)
            }
            R.id.veggie ->{
                order.pizzaType = getString(R.string.veggie)
            }
            R.id.mediterranean ->{
                order.pizzaType = getString(R.string.mediterranean)
                }
            R.id.cheddarSupreme -> {
                order.pizzaType = getString(R.string.cheddar_supreme)
            }
        }

        // Call pizza size activity and pass order object through intent
        val intent = Intent(this@MainActivity, PizzaSizeActivity::class.java)
        intent.putExtra("pizzaOrder", order)
        startActivity(intent)
        return true
    }
}