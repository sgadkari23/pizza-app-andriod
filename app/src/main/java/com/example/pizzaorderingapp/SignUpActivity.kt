package com.example.pizzaorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.pizzaorderingapp.viewmodel.UserViewModel

class SignUpActivity : AppCompatActivity() {

    lateinit var userType: String
    // user view model to access user entity in the database
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // initilize user view model
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val userRegisterType = resources.getStringArray(R.array.user_type)
        val spinnerUsertype = findViewById<Spinner>(R.id.userTypeSpinner)

        if (spinnerUsertype != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, userRegisterType)
            userType = userRegisterType[0]
            spinnerUsertype.adapter = adapter
            spinnerUsertype.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    userType = userRegisterType[position]
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }
    }

    // on register button click
    fun userRegisterOnButtonClick(v: View) {
        if(v.id == R.id.userRegisterButton){
            // call user model view to add user to database
            userViewModel.insertData(context = this@SignUpActivity,
                firstName = findViewById<EditText>(R.id.firstNameEditText).text.toString(),
                lastName = findViewById<EditText>(R.id.updateLastNameEditText).text.toString(),
                userName = findViewById<EditText>(R.id.emailIdEditText).text.toString(),
                roleType = userType,
                password = findViewById<EditText>(R.id.updatepasswordEditText).text.toString())

            // start login activity
            val intent = Intent(this@SignUpActivity, UserLoginActivity::class.java)
            startActivity(intent)
        }
    }
}
