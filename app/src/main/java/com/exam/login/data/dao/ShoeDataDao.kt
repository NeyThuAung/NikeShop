package com.exam.login.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.concurrent.Flow
import com.exam.login.data.entity.ShoeData as ShoeData

@Dao
interface ShoeDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoeData(
        shoeData: ShoeData
    )

    @Query("""
        SELECT *
        FROM ShoeData
        WHERE LOWER(name) LIKE '%' || LOWER(:searchQuery) || '%' OR
                LOWER(subname) LIKE '%' || LOWER(:searchQuery) || '%'
    """)
    fun searchDatabase(searchQuery : String): LiveData<List<ShoeData>>

    /*@Query( "SELECT * FROM ShoeData")
    fun getShoeData(): LiveData<List<ShoeData>>*/

    @Query("DELETE FROM ShoeData")
    suspend fun deleteAllShoes()

    @Query( "SELECT * FROM ShoeData WHERE id=:id")
    fun getShoeData(id : Int): LiveData<ShoeData>
}