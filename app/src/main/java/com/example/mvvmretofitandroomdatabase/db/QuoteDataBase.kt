package com.example.mvvmretrofitdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmretrofitdemo.models.Result

@Database(entities = [Result::class], version = 1)
abstract class QuoteDataBase:RoomDatabase() {

    abstract fun quoteDao():QuoteDao

    companion object{
        @Volatile
        private var INSTANCE: QuoteDataBase? = null

        fun getDatabase(context: Context):QuoteDataBase{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context,
                        QuoteDataBase::class.java,
                        "quoteDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}