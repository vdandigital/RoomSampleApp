package com.example.roomsampleapp.model

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface PersonDAO {

    @Query("Select * from Person")
    fun daoGetAllPerson():LiveData<List<Person>>

    @Insert
    suspend fun daoInsert(vararg person:Person)

    @Query ("select * from Person where first_name like :name")
    suspend fun daoSearchByFirstName(name:String):Person

    @Update
    suspend fun daoUpdatePerson(person:Person)

    @Delete
    suspend fun daoDeletePerson(person:Person)
}