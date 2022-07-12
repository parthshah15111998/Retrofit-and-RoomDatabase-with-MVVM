package com.example.mvvmretofitandroomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretrofitdemo.mainviewmodel.MainViewModel
import com.example.mvvmretrofitdemo.mainviewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = (application as QuoteApplication).quoteRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
        mainViewModel.quotes.observe(this, Observer {
            //Log.d("MainActivity",it.results.toString())
            Toast.makeText(this,it.results.size.toString(), Toast.LENGTH_LONG).show()
            //check logcat and appInspection for database

        })
    }
}