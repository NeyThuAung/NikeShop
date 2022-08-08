package com.exam.login.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderData(
    @PrimaryKey(autoGenerate = true)
    val orderId: Int ?= null,
    val orderImage: String,
    val orderName: String,
    val orderSubName: String,
    val orderPrice: String,
    val size: String,
    val quatity: String
)
