package com.example.mvvmretofitandroomdatabase

import android.app.Application
import com.example.mvvmretrofitdemo.api.QuoteServices
import com.example.mvvmretrofitdemo.api.RetrofitHelper
import com.example.mvvmretrofitdemo.db.QuoteDataBase
import com.example.mvvmretrofitdemo.repository.QuoteRepository

class QuoteApplication:Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize(){
        val quoteServices = RetrofitHelper.getInstance().create(QuoteServices::class.java)
        val dataBase = QuoteDataBase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteServices,dataBase,applicationContext)
    }
}