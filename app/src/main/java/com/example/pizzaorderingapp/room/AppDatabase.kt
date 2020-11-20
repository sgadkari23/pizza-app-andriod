package com.example.pizzaorderingapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pizzaorderingapp.model.OrderEntity
import com.example.pizzaorderingapp.model.UserEntity

@Database(entities = [UserEntity::class, OrderEntity::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

     abstract fun dao() : DAOAccess

     companion object {

          @Volatile
          private var INSTANCE: AppDatabase? = null

          fun getDataseClient(context: Context) : AppDatabase {

               if (INSTANCE != null) return INSTANCE!!

               synchronized(this) {
                    INSTANCE = Room
                         .databaseBuilder(context, AppDatabase::class.java, "App_Database")
                         .fallbackToDestructiveMigration()
                         .build()

                    return INSTANCE!!
               }
          }
     }
}