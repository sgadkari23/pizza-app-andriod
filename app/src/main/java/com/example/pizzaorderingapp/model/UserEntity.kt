package com.example.pizzaorderingapp.model
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: user table
* */
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// storing user (customer or admin) information
@Entity(tableName = "user")
data class UserEntity (
    @ColumnInfo(name = "firstName")var firstName:String,
    @ColumnInfo(name = "lastName")var lastName:String,
    @ColumnInfo(name = "userName")var userName:String,
    @ColumnInfo(name = "roleType")var roleType:String,
    @ColumnInfo(name = "password")var password:String,
    ){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "customerId")
    var id: Int? = null
}