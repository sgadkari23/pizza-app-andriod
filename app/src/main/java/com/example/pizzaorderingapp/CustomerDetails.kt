package com.example.pizzaorderingapp
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Customer performation form
* */
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.pizzaorderingapp.room.AppDatabase
import com.example.pizzaorderingapp.viewmodel.UserViewModel

class CustomerDetails : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel

    var order:Order? = null
    val personalInformation = PersonalInformation()

    lateinit var fullName:EditText
    lateinit var userName:String

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_customer_details)

         // Extract order object from intent
         order = intent.extras?.get("pizzaOrder") as Order
         userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

         fullName = findViewById<EditText>(R.id.editTextTextPersonName)

         // extract username from shared preference
         val sharedPref = this@CustomerDetails?.getSharedPreferences("LoggedInUser", Context.MODE_PRIVATE)

         userName = sharedPref.getString("userName", "")!!

         // Extract firstname and last name of logged in user
         userViewModel.getUserDetails(this@CustomerDetails, userName)!!.observe(this, Observer {

             if (it == null) {
                 Toast.makeText(this, "Error fetching logged in user info.", Toast.LENGTH_SHORT).show()
             }
             else {
                 fullName.setText(it.firstName + " " + it.lastName)
             }
         })

         // Get selected payment cardtype from dropdown/select menu
         val paymentcardtype = resources.getStringArray(R.array.payment_cardtype)
         val spinnerCardtype = findViewById<Spinner>(R.id.spinnerCardtype)

         if (spinnerCardtype != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, paymentcardtype)
            spinnerCardtype.adapter = adapter
            spinnerCardtype.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    personalInformation.cardType = paymentcardtype[position]
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }
    }

    fun onclickOnCustomerdetailsButtonClick(v: View) {
        if (v.id == R.id.buttonNext) {
            // Validate user input
            // Full name is required

            if(fullName.text.isEmpty()){
                Toast.makeText(this, "Full name is required", Toast.LENGTH_SHORT).show()
                return
            }

            // phone number must be 10 digits
            val phoneNo = findViewById<EditText>(R.id.editTextPhoneNo)
            val patternPhoneNo = Regex("^\\d{10}\$")
            if(!patternPhoneNo.matches(phoneNo.text)) {
                Toast.makeText(this, "Please enter valid 10 digit phone number", Toast.LENGTH_SHORT).show()
                return
            }

            // postal code is of format: a1a1a1
            val postalCode = findViewById<EditText>(R.id.editTextTextPostalAddress)
            val patternPostalCode = Regex("^[A-Za-z]\\d[A-Za-z]\\d[A-Za-z]\\d$")
            if(!patternPostalCode.matches(postalCode.text))
            {
                Toast.makeText(this, "Please postal code of format: A1A1A1", Toast.LENGTH_SHORT).show()
                return
            }

            // card number must be 16 digits
            val cardNo = findViewById<EditText>(R.id.editTextNumberCardCardno)
            val patternCardNo = Regex("^\\d{16}\$")
            if(!patternCardNo.matches(cardNo.text)) {
                Toast.makeText(this, "Please enter valid 16 digit card number", Toast.LENGTH_SHORT).show()
                return
            }

            // Address is required
            val address = findViewById<EditText>(R.id.editTextTextAddress)
            if(address.text.isEmpty()){
                Toast.makeText(this, "Address is required", Toast.LENGTH_SHORT).show()
                return
            }

            // card expiry must be of format mm/yyyy
            val cardExpiry = findViewById<EditText>(R.id.editTextCardExpiry)
            val patternCardExpiry = Regex("(0[1-9]|10|11|12)/20[0-9]{2}\$")
            if(!patternCardExpiry.matches(cardExpiry.text)) {
                Toast.makeText(this, "Please enter valid card expiry data (MM/YYYY)", Toast.LENGTH_SHORT).show()
                return
            }

            // If validation succeeds, add data to personal information object
            personalInformation.fullName = fullName.text.toString()
            personalInformation.address = address.text.toString()
            personalInformation.mobileNo = phoneNo.text.toString()
            personalInformation.postalCode = postalCode.text.toString()
            personalInformation.cardNumber = cardNo.text.toString()
            personalInformation.cardExpiry = cardExpiry.text.toString()
            personalInformation.userName = userName

            // Call checkout activity with intent data
            val intent = Intent(this@CustomerDetails, CheckoutActivity::class.java)
            intent.putExtra("pizzaOrder", order)
            intent.putExtra("customerInfo", personalInformation)
            startActivity(intent)
        }
    }
}