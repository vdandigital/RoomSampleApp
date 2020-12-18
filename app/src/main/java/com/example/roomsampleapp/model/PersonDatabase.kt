package com.example.roomsampleapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[Person::class],version=1)
abstract class  PersonDatabase:RoomDatabase() {

    abstract fun personDao():PersonDAO

    companion object {
        @Volatile
        private var INSTANCE:PersonDatabase?=null

        fun getInstance(context:Context):PersonDatabase {
            synchronized(this) {
                var instance:PersonDatabase?= INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PersonDatabase::class.java,
                        "persondb"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }

}