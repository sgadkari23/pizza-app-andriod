package com.example.pizzaorderingapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pizzaorderingapp.model.UserEntity
import com.example.pizzaorderingapp.repository.AppRepository
// Interface layer between the database repository and UI activity
class UserViewModel: ViewModel() {

    var liveDataLogin: LiveData<UserEntity>? = null

    // insert user
    fun insertData(context: Context, firstName: String, lastName: String, userName: String,  password: String,  roleType: String) {
        AppRepository.insertData(context, firstName = firstName, lastName = lastName, userName = userName, roleType = roleType, password = password)
    }

    // get user by username
    fun getUserDetails(context: Context, emailId: String) : LiveData<UserEntity>? {
        liveDataLogin = AppRepository.getUserDetails(context, emailId)
        return liveDataLogin
    }

    // update user
    fun updateUser(context: Context,userEntity: UserEntity){
        AppRepository.updateUser(context,userEntity)
    }
}
