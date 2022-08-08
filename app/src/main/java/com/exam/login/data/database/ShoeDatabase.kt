package com.exam.login.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.exam.login.data.dao.OrderDataDao
import com.exam.login.data.dao.ShoeDataDao
import com.exam.login.data.entity.OrderData
import com.exam.login.data.entity.ShoeData

@Database(
    entities = [ShoeData::class,OrderData::class], version = 2, exportSchema = false
)
abstract class ShoeDatabase : RoomDatabase(){

    abstract val shoeDataDao : ShoeDataDao
    abstract val orderDataDao : OrderDataDao

    companion object{
        private var INSTANCE : ShoeDatabase ?= null
        fun getDatabase(context: Context) : ShoeDatabase{
            if (INSTANCE==null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    ShoeDatabase::class.java,
                    "localClassDb",
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }

}