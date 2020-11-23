package com.example.pizzaorderingapp
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Update user profile
* */
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
    // variable declaration
    lateinit var userViewModel: UserViewModel
    lateinit var userName: String
    lateinit var userEditEntity: UserEntity
    lateinit var firstNameEditText: EditText
    lateinit var lastNameEditText: EditText
    lateinit var passwordNameEditText: EditText
    lateinit var userNameEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        firstNameEditText = findViewById<EditText>(R.id.updateFirstNameEditText)
        lastNameEditText = findViewById<EditText>(R.id.updateLastNameEditText)
        passwordNameEditText = findViewById<EditText>(R.id.updatepasswordEditText)
        userNameEditText = findViewById<EditText>(R.id.updateUsernameEditText)

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
                userNameEditText.setText(it.userName)
                userEditEntity = it
            }
        })
    }
// function called update button pressed
    fun onUpdateButtonPressed(v:View){
        if(v.id == R.id.updateProfileButton) {
            userEditEntity.firstName = firstNameEditText.text.toString()
            userEditEntity.lastName = lastNameEditText.text.toString()
            userEditEntity.password = passwordNameEditText.text.toString()
            userEditEntity.userName = userNameEditText.text.toString()

            userViewModel.updateUser(
                context = this@EditProfileActivity,
                userEditEntity
            )
            if(userEditEntity.roleType =="customer") {
                val intent = Intent(this@EditProfileActivity, MainActivity::class.java)
                Toast.makeText(this, "Personal information updated.", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }else{
                val intent = Intent(this@EditProfileActivity, AdminHomeActivity::class.java)
                Toast.makeText(this, "Personal information updated.", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }

        }
    }

}