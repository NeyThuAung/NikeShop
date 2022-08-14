package com.exam.login.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import coil.load
import com.exam.login.card.ListActivity
import com.exam.login.card.OrderActivity
import com.exam.login.data.entity.OrderData
import com.exam.login.databinding.ActivityDetailBinding
import com.exam.login.login.LoginActivity
import com.exam.login.util.AppSharedPreference
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
    private  val sharedPref : AppSharedPreference by lazy {
        AppSharedPreference(this)
    }
    private var isLogin =false
    private val orderViewModel : OrderViewModel by viewModels()
    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        shoeId=intent.getIntExtra("shoeId",0).toInt()
        isLogin = sharedPref.getValueBoolean("IsLogin",false)
        //getShoeData(shoeId)
        onClick()
        getShoeData(shoeId)
    }

    private fun onClick(){
        binding.bag.setOnClickListener {


            if (isLogin){
                val intent = Intent(this, OrderActivity::class.java)
                startActivity(intent)

            }else{
                val intent= Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
        }
        binding.btnAddToCart.setOnClickListener {
            if (isLogin){
                viewModel.getShoeData(shoeId).observe(this){
                    orderImg =it.imglink.toString()
                    Log.i("DetailActivity", "$orderImg")
                    orderName = it.name.toString()
                    Log.i("DetailActivity", "$orderName")
                    orderSub = it.subname.toString()
                    orderPrice = it.price

                    val size = binding.spinnerSize.selectedItem.toString()
                    val quantity = binding.spinnerQuantity.selectedItem.toString()
                    val orderData = OrderData(
                        null,orderImg,orderName,orderSub,orderPrice,size,quantity
                    )

                    orderViewModel.insertOrder(orderData)
                }


            }else{
                val intent= Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }

        }
    }


    fun getShoeData(id : Int){
        viewModel.getShoeData(id).observe(this){
            binding.shoeImg.load(it.imglink)
            binding.tvShoeName.text=it.name
            binding.tvShoeSub.text=it.subname
            binding.tvShoePrice.text=it.price.toString()
            binding.tvDesc.text=it.desc

        }
    }


}