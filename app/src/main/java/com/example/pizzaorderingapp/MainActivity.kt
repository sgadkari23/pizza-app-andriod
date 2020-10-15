package com.example.pizzaorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.example.pizzaorderingapp.R.id.meatSupreme
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.pizza_types,menu)
        return true
    }

    class order:Serializable{
        
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            meatSupreme ->{
            val intent = Intent(this@MainActivity, CheckoutActivity::class.java)
              //  val mnuMeatSupreme: String = getString(R.string.meat_supreme)
              //  val pizzaType: String = getString(R.string.meat_supreme)
                intent.putExtra("PizzaMenueType", getString(R.string.meat_supreme))
                Toast.makeText(this, "You clicked Meat Supreme", Toast.LENGTH_LONG).show()
                startActivity(intent)
            }
            R.id.superHawaiian ->{
                val intent = Intent(this@MainActivity, CheckoutActivity::class.java)
                intent.putExtra("PizzaMenueType", getString(R.string.super_hawaiian))
                startActivity(intent)
                Toast.makeText(this, "You clicked Super Hawaiian", Toast.LENGTH_LONG).show()
            }

            R.id.veggie ->{
                val intent = Intent(this@MainActivity, CheckoutActivity::class.java)
                intent.putExtra("PizzaMenueType", getString(R.string.veggie))
                startActivity(intent)
                Toast.makeText(this, "You clicked Veggie", Toast.LENGTH_LONG).show()
            }

            R.id.mediterranean ->{
                val intent = Intent(this@MainActivity, CheckoutActivity::class.java)
                intent.putExtra("PizzaMenueType", getString(R.string.mediterranean))
                startActivity(intent)
                Toast.makeText(this, "You clicked Mediterranean", Toast.LENGTH_LONG).show()
            }

            R.id.cheddarSupreme ->{
                val intent = Intent(this@MainActivity, CheckoutActivity::class.java)
                intent.putExtra("PizzaMenueType", getString(R.string.cheddar_supreme))
                startActivity(intent)
                Toast.makeText(this, "You clicked Cheddar Supreme", Toast.LENGTH_LONG).show()
            }
        }

        return true
    }


}