package com.example.mvvmretrofitdemo.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmretrofitdemo.api.QuoteServices
import com.example.mvvmretrofitdemo.db.QuoteDataBase
import com.example.mvvmretrofitdemo.models.QuoteList
import com.example.mvvmretrofitdemo.utils.NetworkUtils

class QuoteRepository(
    private val quoteServices: QuoteServices,
    private val quoteDataBase: QuoteDataBase,
    private val applicationContext: Context
) {

    private val quoteLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
    get() = quoteLiveData


    suspend fun getQuotes(page:Int){

        if (NetworkUtils.isInternetAvailable(applicationContext)){
            val result = quoteServices.getQuote(page)
            if (result?.body() != null){
                quoteDataBase.quoteDao().addQuotes(result.body()!!.results)
                quoteLiveData.postValue(result.body())
            }
        }else{
            val quote = quoteDataBase.quoteDao().getQuotes()
            val quoteList = QuoteList(1,1,1,quote,1,1)
            quoteLiveData.postValue(quoteList)
        }


    }
}