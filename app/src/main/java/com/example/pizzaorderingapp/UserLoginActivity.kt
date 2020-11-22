package com.example.pizzaorderingapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.pizzaorderingapp.room.AppDatabase
import com.example.pizzaorderingapp.viewmodel.UserViewModel

class UserLoginActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
        // initializr user view model
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val signupClickableText = findViewById(R.id.signupTextView) as TextView
        signupClickableText.setOnClickListener {
            val intent = Intent(this@UserLoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    // on login button click
    fun loginButtonOnClick(v:View){
        if(v.id == R.id.loginButton){
            var username = findViewById<EditText>(R.id.usernameEditText).text.toString()
            var loginPassword = findViewById<EditText>(R.id.passwordEditText).text.toString()

            // validation of user input password
            if(username.isNotEmpty() && loginPassword.isNotEmpty()) {

                // get user details from database
                userViewModel.getUserDetails(this@UserLoginActivity, username)!!.observe(this, Observer {

                    //cannot find user in database
                    if (it == null) {
                        Toast.makeText(this, "Cannot find user. Please sign up!", Toast.LENGTH_LONG).show()
                    }
                    else {
                        // check if password match
                        if (it.password == loginPassword) {
                            // password matched

                            // store username and role in shared preference
                            val sharedPref = this@UserLoginActivity?.getSharedPreferences("LoggedInUser",Context.MODE_PRIVATE)
                            with (sharedPref.edit()) {
                                putString("userName", username)
                                putString("role", it.roleType)
                                apply()
                            }

                            Toast.makeText(this, "Login successful. Welcome!", Toast.LENGTH_LONG).show()

                            if(it.roleType == "admin")
                            {
                                // if user is admin, start admin home activity
                                val intent = Intent(this@UserLoginActivity, AdminHomeActivity::class.java)
                                startActivity(intent)
                            }
                            else
                            {
                                // if user is customer, start main activity
                                val intent = Intent(this@UserLoginActivity, MainActivity::class.java)
                                startActivity(intent)
                            }

                        } else {
                            // password incorrect
                            Toast.makeText(this, "Incorrect password. Please try again", Toast.LENGTH_LONG).show()
                        }
                    }
                })
            } else {
                Toast.makeText(this, "Please enter valid username and password", Toast.LENGTH_LONG).show()
            }
        }
    }

}