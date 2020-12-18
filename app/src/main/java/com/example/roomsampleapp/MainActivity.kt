package com.example.roomsampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomsampleapp.databinding.ActivityMainBinding
import com.example.roomsampleapp.model.PersonDatabase
import com.example.roomsampleapp.model.PersonRepository
import com.example.roomsampleapp.viewmodel.PersonViewModel
import com.example.roomsampleapp.viewmodel.PersonViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private lateinit var personViewModel:PersonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        val personDao = PersonDatabase.getInstance(application).personDao()
        val repository= PersonRepository(personDao)
        val factory=PersonViewModelFactory(repository)

        personViewModel = ViewModelProvider(this,factory).get(PersonViewModel::class.java)
        binding.personViewModel = personViewModel
        binding.lifecycleOwner=this

        personViewModel.persons.observe(
            this, Observer {
                Log.d("PersonTest",it.toString())
            }
        )

    }
}