package com.example.roomsampleapp.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.example.roomsampleapp.model.PersonRepository
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomsampleapp.model.Person
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class PersonViewModel(private val personRepository: PersonRepository):ViewModel(), Observable {
    val persons = personRepository.person

    var isUpdateOrDelete = false

    private lateinit var personToUpdateOrDelete:Person

    @Bindable
    val inputFirstName = MutableLiveData<String>()
    @Bindable
    val inputLastName = MutableLiveData<String>()
    @Bindable
    val inputAge = MutableLiveData<String>()
    @Bindable
    val inputEmail = MutableLiveData<String>()
    @Bindable
    val inputAddress = MutableLiveData<String>()
    @Bindable
    val inputDob = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateBtnTxt = MutableLiveData<String>()

    @Bindable
    val clearAll = MutableLiveData<String>()

    init {
        saveOrUpdateBtnTxt.value = "Insert"
        clearAll.value = "Clear All"
    }

    fun clearAllOrDelete() {
        if(isUpdateOrDelete == true) {
            deletePerson(personToUpdateOrDelete)
            clearAllFields()
        }
        else {
            clearAllFields()
        }
    }

    fun clearAllFields() {
        Log.d("TEst","Clearfields")
        inputFirstName.value = ""
        inputLastName.value = ""
        inputAge.value =""
        inputAddress.value = ""
        inputEmail.value=""
        inputDob.value=""
        clearAll.value = "Clear All"
        saveOrUpdateBtnTxt.value = "Insert"
        isUpdateOrDelete = false
    }

    fun insertOrUpdate(){
        Log.d("Test", inputFirstName.value.toString())
        if (isUpdateOrDelete == false) {
            insertPerson(Person(
                inputFirstName.value!!,
                inputLastName.value!!,
                inputAge.value!!.toInt(),
                inputEmail.value!!,
                inputAddress.value!!,
                inputDob.value!!))
                clearAllFields()
            }
        else {
            personToUpdateOrDelete.first_name = inputFirstName.value!!
            personToUpdateOrDelete.last_name = inputLastName.value!!
            personToUpdateOrDelete.age = inputAge.value!!.toInt()
            personToUpdateOrDelete.email= inputEmail.value!!
            personToUpdateOrDelete.address = inputAddress.value!!
            personToUpdateOrDelete.dob = inputDob.value!!

            updatePerson(personToUpdateOrDelete)
        }

    }

    private fun updatePerson(person:Person) {
        viewModelScope.launch {
            personRepository.personRepoUpdatePerson(person)
            clearAllFields()
        }
    }

    fun deletePerson(person:Person) {
        viewModelScope.launch {
            personRepository.personRepoDeletePerson(person)
            clearAllFields()
        }
    }

    private fun insertPerson(person:Person){
        viewModelScope.launch {
            personRepository.personRepoInsert(person)
            Log.d("Test","Inserted")
        }

    }

    fun searchByFirstName() {

        viewModelScope.launch {
            val personByFirstName = personRepository.personRepoSearchByFirstName(inputFirstName.value!!)
            if(personByFirstName!=null){
                inputFirstName.value = personByFirstName.first_name
                inputLastName.value = personByFirstName.last_name
                inputAge.value = personByFirstName.age.toString()
                inputAddress.value=personByFirstName.address
                inputEmail.value   = personByFirstName.email
                inputDob.value=personByFirstName.dob

                isUpdateOrDelete = true
                saveOrUpdateBtnTxt.value = "Update"
                clearAll.value = "Delete"
                personToUpdateOrDelete = personByFirstName
            }
            else{
                Log.d("PersonTest","No Records Found")
                clearAllFields()
            }
        }

    }



    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}