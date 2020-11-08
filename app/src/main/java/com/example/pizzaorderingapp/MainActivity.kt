package com.example.pizzaorderingapp
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Home screen of Pizza app
* */
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            val clickableText = findViewById(R.id.pizzaStoresTextView) as TextView
        clickableText.setOnClickListener {
            // your code to perform when the user clicks on the TextView
            //Toast.makeText(this@MainActivity, "You clicked on TextView 'Click Me'.", Toast.LENGTH_SHORT).show()
           // val intent = Intent(this@MainActivity, PizzaStoresActivity::class.java) GoogleMaps
            val intent = Intent(this@MainActivity, PizzaStoreCitiesActivity::class.java)
            startActivity(intent)

        }
        // Initialize the SDK
        //Places.initialize(applicationContext, "AIzaSyBsB5J1cPKtF9IwE1x7ZcbkfV1K-jbxPJE")

        // Create a new PlacesClient instance
        //val placesClient = Places.createClient(this)
        //placesClient.fetchPlace()
        //textView.movementMethod = LinkMovementMethod.getInstance()
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