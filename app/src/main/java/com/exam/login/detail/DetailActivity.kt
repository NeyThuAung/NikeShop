package com.exam.login.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import coil.load
import com.exam.login.data.entity.OrderData
import com.exam.login.databinding.ActivityDetailBinding
import com.exam.login.viewModel.OrderViewModel
import com.exam.login.viewModel.ShoeViewModel

class DetailActivity : AppCompatActivity() {
    private var shoeId : Int=0
    /*private var saleLevel : String = ""
    private var shoeName : String = ""
    private var shoeSub : String = ""
    private var shoePrice : String = ""
    private var shoeDesc : String = ""*/
    private var orderImg : String =""
    private var orderName : String =""
    private var orderSub : String=""
    private var orderPrice : String=""
    private val viewModel : ShoeViewModel by viewModels()
    private val orderViewModel : OrderViewModel by viewModels()
    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        shoeId=intent.getIntExtra("shoeId",0).toInt()
        //getShoeData(shoeId)
        onClick()
        getShoeData(shoeId)
    }

    private fun onClick(){
        binding.btnAddToCart.setOnClickListener {
            getShoeData(shoeId)
        }
    }


    fun getShoeData(id : Int){
        viewModel.getShoeData(id).observe(this){
            binding.shoeImg.load(it.imglink)
            binding.tvShoeName.text=it.name
            binding.tvShoeSub.text=it.subname
            binding.tvShoePrice.text=it.price
            binding.tvDesc.text=it.desc
            orderImg =it.imglink.toString()
            Log.i("DetailActivity", "$orderImg")
            orderName = it.name.toString()
            Log.i("DetailActivity", "$orderName")
            orderSub = it.subname.toString()
            orderPrice = it.price.toString()

            val size = binding.spinnerSize.selectedItem.toString()
            val quantity = binding.spinnerQuantity.selectedItem.toString()
            val orderData = OrderData(
                null,orderImg,orderName,orderSub,orderPrice,size,quantity
            )

            orderViewModel.insertOrder(orderData)
        }
    }


}