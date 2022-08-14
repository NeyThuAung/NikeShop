package com.exam.login.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exam.login.data.entity.OrderData
import com.exam.login.data.entity.ShoeData


@Dao
interface OrderDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderData(
        orderData: OrderData
    )

    @Query( "SELECT * FROM OrderData")
    fun getOrderData(): LiveData<List<OrderData>>

    @Query("DELETE FROM OrderData WHERE orderId=:id")
    suspend fun deleteOrder(id : Int)

    @Query("DELETE FROM OrderData")
    suspend fun deleteAllOrders()

}