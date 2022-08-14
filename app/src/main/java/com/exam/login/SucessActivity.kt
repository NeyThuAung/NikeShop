package com.exam.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.exam.login.card.ListActivity
import com.exam.login.databinding.ActivitySucessBinding
import com.exam.login.viewModel.OrderViewModel
import com.exam.login.viewModel.ShoeViewModel

class SucessActivity : AppCompatActivity() {
    private val viewModel : OrderViewModel by viewModels()
    private lateinit var binding : ActivitySucessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_sucess)
        binding =ActivitySucessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnOk.setOnClickListener {
            val intent = Intent(this@SucessActivity,ListActivity::class.java)
            startActivity(intent)
            viewModel.deleteAllOrders()

            finish()

        }
    }
}