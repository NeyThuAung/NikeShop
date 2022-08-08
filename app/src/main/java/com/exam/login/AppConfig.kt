package com.exam.login

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exam.login.data.database.ShoeDatabase

class AppConfig : Application() {
    lateinit var database: ShoeDatabase

    override fun onCreate() {
        super.onCreate()
        database = ShoeDatabase.getDatabase(applicationContext)
    }
}