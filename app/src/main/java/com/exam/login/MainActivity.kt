package com.exam.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.exam.login.databinding.ActivityMainBinding
import com.exam.login.databinding.ActivityMainWithMaterialBinding
import com.exam.login.databinding.RegisterBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainWithMaterialBinding
    private  lateinit var loginButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainWithMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //LoginButton = findViewById(R.id.btn_login)
        //binding.btnLogin.setOnClickListener {
            //Toast.makeText(this@MainActivity, "Welcome", Toast.LENGTH_SHORT).show()
        //}
    }
}