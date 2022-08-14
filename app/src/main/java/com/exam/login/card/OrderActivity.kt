package com.exam.login.card

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.login.SucessActivity
import com.exam.login.card.adapter.OrderAdapter
import com.exam.login.data.entity.OrderData
import com.exam.login.databinding.ActivityOrderBinding
import com.exam.login.databinding.ActivitySucessBinding
import com.exam.login.viewModel.OrderViewModel

class OrderActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOrderBinding
    private lateinit var orderAdapter: OrderAdapter
    private lateinit var orderList : ArrayList<OrderData>
    private val orderViewModel : OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_order)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        orderList = arrayListOf()
        initRec()
        getOrderData()
        onClick()
    }
    private fun onClick(){

        binding.btnBuy.setOnClickListener {
            val intent = Intent(this@OrderActivity,SucessActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initRec(){
        orderAdapter= OrderAdapter(orderList,orderViewModel)
        binding.recOrder.also {
            it.setHasFixedSize(true)
            it.layoutManager= LinearLayoutManager(this@OrderActivity)
            it.adapter = orderAdapter
        }
    }

    private fun getOrderData(){
        orderViewModel.getOrderData().observe(this){
            orderList = it as ArrayList<OrderData>
            orderAdapter.setData(orderList)
        }
    }

}