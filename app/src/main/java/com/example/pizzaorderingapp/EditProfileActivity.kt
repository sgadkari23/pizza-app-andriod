package com.example.pizzaorderingapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pizzaorderingapp.model.UserEntity
import com.example.pizzaorderingapp.viewmodel.UserViewModel

class EditProfileActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel
    lateinit var userName: String
    lateinit var userEditEntity: UserEntity
    lateinit var firstNameEditText: EditText
    lateinit var lastNameEditText: EditText
    lateinit var passwordNameEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        firstNameEditText = findViewById<EditText>(R.id.updateFirstNameEditText)
        lastNameEditText = findViewById<EditText>(R.id.updateLastNameEditText)
        passwordNameEditText = findViewById<EditText>(R.id.passwordEditText)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val sharedPref = this@EditProfileActivity?.getSharedPreferences("LoggedInUser", Context.MODE_PRIVATE)
        userName = sharedPref.getString("userName", "")!!

        userViewModel.getUserDetails(this@EditProfileActivity, userName)!!.observe(this, Observer {

            if (it == null) {
                Toast.makeText(this, "Error fetching logged in user info.", Toast.LENGTH_SHORT).show()
            }
            else {

                firstNameEditText.setText(it.firstName)
                lastNameEditText.setText(it.lastName)
                passwordNameEditText.setText(it.password)
                userEditEntity = it
            }
        })
    }
//findViewById<EditText>(R.id.updateLastNameEditText).text.toString(),
    fun onUpdateButtonPressed(v:View){
        if(v.id == R.id.updateProfileButton) {
            userEditEntity.firstName = firstNameEditText.text.toString()
            userEditEntity.lastName = lastNameEditText.text.toString()
            userEditEntity.password = passwordNameEditText.text.toString()

            userViewModel.updateUser(
                context = this@EditProfileActivity,
                userEditEntity
            )
            val intent = Intent(this@EditProfileActivity, MainActivity::class.java)
            Toast.makeText(this, "Personal information updated.", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }

}