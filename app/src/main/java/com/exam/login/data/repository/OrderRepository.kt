package com.exam.login.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.exam.login.AppConfig
import com.exam.login.data.entity.OrderData
import com.exam.login.data.entity.ShoeData

class OrderRepository(var application: Application) {
    val db =(application as AppConfig).database

    suspend fun insertOrderData(orderData: OrderData){
        db.orderDataDao.insertOrderData(orderData)
    }

    fun getOrderData(): LiveData<OrderData> {
        return db.orderDataDao.getOrderData()
    }


}