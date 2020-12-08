package com.example.pizzaorderingapp
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Home screen of Pizza app
* */

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.IBinder
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
           //  setContentView(R.layout.activity_checkout)
        val intent = Intent(this@MainActivity, AndroidBackgroundServices::class.java)
        startService(intent)
    }
    // intent call on store button click
  fun onPlaceOrderButtonClicked(v: View){
        val intent = Intent(this@MainActivity, PizzaTypeActivity::class.java)
        startActivity(intent)
    }

  /*fun onPlayMusicButtonClicked(v: View){
        val intent = Intent(this@MainActivity, AndroidBackgroundServices::class.java)
        startService(intent)
  }

    fun onStopMusicButtonClicked(v: View){
        val intent = Intent(this@MainActivity, AndroidBackgroundServices::class.java)
        stopService(intent)
    }
    // intent call on update button click
   fun onUpdateProfileButtonClicked(v:View){
        val intent = Intent(this@MainActivity, EditProfileActivity::class.java)
        startActivity(intent)
    }
    // User Logout
    fun onLogoutButtonPressed(v: View){
        if(v.id == R.id.placeOrderButton){
            val intent = Intent(this@MainActivity, UserLoginActivity::class.java)
            Toast.makeText(this, "Logout successfully!", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
    }
*/
    // Populate context menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.customer_activities, menu)
        return true
    }

    // Handler when user selects a pizza from context menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Create new object of type Order


        // Set appropriate pizza type in order object based on user selection
        when(item.itemId){
            R.id.customerOrderHistory -> {
                val intent = Intent(this@MainActivity, GetAllOrdersOfCustomerActivity::class.java)
                startActivity(intent)
            }
            R.id.customerProfileUpdate -> {
                val intent = Intent(this@MainActivity, EditProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.customerPizzaStore -> {
                val intent = Intent(this@MainActivity, PizzaStoreCitiesActivity::class.java)
                startActivity(intent)
            }
            R.id.customerLogout -> {
                val stopIntent = Intent(this@MainActivity, AndroidBackgroundServices::class.java)
                stopService(stopIntent)
                val intent = Intent(this@MainActivity, UserLoginActivity::class.java)
                Toast.makeText(this, "Logout successfully!", Toast.LENGTH_LONG).show()
                startActivity(intent)
            }

        }
        // Call pizza size activity and pass order object through intent
    /*    val intent = Intent(this@MainActivity, PizzaSizeActivity::class.java)
        intent.putExtra("pizzaOrder", order)
        startActivity(intent) */
        return true
    }
}