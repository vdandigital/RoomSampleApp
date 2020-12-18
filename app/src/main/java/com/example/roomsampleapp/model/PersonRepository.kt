package com.example.roomsampleapp.model

class PersonRepository(private val personDao:PersonDAO) {

    val person = personDao.daoGetAllPerson()

    suspend fun personRepoInsert(person:Person) {
        personDao.daoInsert(person)
    }

    suspend fun personRepoSearchByFirstName(name:String):Person {
        return personDao.daoSearchByFirstName(name)
    }

    suspend fun personRepoUpdatePerson(person:Person) {
        personDao.daoUpdatePerson(person)
    }

    suspend fun personRepoDeletePerson(person:Person) {
        personDao.daoDeletePerson(person)
    }

}