package com.example.roomsampleapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity

data class Person(
    @ColumnInfo(name="First_name") var first_name:String,
    @ColumnInfo(name="Last_name") var last_name:String,
    @ColumnInfo(name="Age") var age:Int,
    @ColumnInfo(name="Email") var email:String,
    @ColumnInfo(name="Address") var address:String,
    @ColumnInfo(name="DOB") var dob:String
)
{
    @PrimaryKey(autoGenerate = true) var PersonID:Int?=null
}



