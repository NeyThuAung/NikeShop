package com.exam.login.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShoeData(
    @PrimaryKey (autoGenerate = true)
    val id :Int ?= null,
    val imglink : String,
    val salelevel : String,
    val name : String,
    val subname : String,
    val price : String,
    val desc : String
)
