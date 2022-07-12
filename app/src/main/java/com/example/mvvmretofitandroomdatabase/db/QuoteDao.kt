package com.example.mvvmretrofitdemo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmretrofitdemo.models.Result

@Dao
interface QuoteDao {

    @Insert
     fun addQuotes(quote:List<Result>)

    @Query("SELECT * FROM quote")
     fun getQuotes():List<Result>
}