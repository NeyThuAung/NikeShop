package com.exam.login.insertData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.exam.login.data.entity.ShoeData
import com.exam.login.databinding.ActivityShoeInsertBinding
import com.exam.login.viewModel.ShoeViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShoeInsertActivity : AppCompatActivity() {

    private val viewModel :ShoeViewModel by viewModels()

    private lateinit var binding: ActivityShoeInsertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoeInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClick()

    }

    private fun onClick(){
        binding.btnAddShoe.setOnClickListener {
            insertShoeData()
          //  finish()
        }
        binding.btnDeleteAll.setOnClickListener {
            viewModel.deleteAllShoes()
        }

    }

    private fun insertShoeData(){
        val imglink = binding.etImgLink.text.toString().trim()
        val salelevel = binding.etSaleLevel.text.toString().trim()
        val name = binding.etShoeName.text.toString().trim()
        val subname = binding.etSubName.text.toString().trim()
        val price = binding.etPrice.text.toString().trim()
        val desc = binding.etDesc.text.toString().trim()
        val shoeData = ShoeData(
            null,imglink, salelevel, name, subname, price, desc
        )
        viewModel.insertShoeForm(shoeData)
    }
}