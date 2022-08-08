package com.exam.login.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Query
import com.exam.login.AppConfig
import com.exam.login.data.dao.ShoeDataDao
import com.exam.login.data.entity.ShoeData

class ShoeRepository(var application: Application) {
    val db=(application as AppConfig).database

    suspend fun insertShoeData(shoeData: ShoeData){
        db.shoeDataDao.insertShoeData(shoeData)
    }

    fun searchDatabase(searchQuery : String) : LiveData<List<ShoeData>>{
        return db.shoeDataDao.searchDatabase(searchQuery)
    }

    /*fun getShoeData(query: String): LiveData<List<ShoeData>>{
        val shoeList = db.shoeDataDao.getShoeData()
        return shoeList
    }*/

    fun getShoeData(id : Int) : LiveData<ShoeData>{
       return db.shoeDataDao.getShoeData(id)
    }

    suspend fun deleteAll() {
        db.shoeDataDao.deleteAllShoes()
    }
}