package com.exam.login.card.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.exam.login.R
import com.exam.login.card.OrderActivity
import com.exam.login.data.entity.OrderData
import com.exam.login.data.entity.ShoeData
import com.exam.login.databinding.ItemOrderBinding
import com.exam.login.viewModel.OrderViewModel

class OrderAdapter(

    private var orderList : ArrayList<OrderData>,
    private var orderViewModel: OrderViewModel


        ): RecyclerView.Adapter<OrderAdapter.OrderHolder>() {

    fun setData(orderList1: ArrayList<OrderData>){
        this.orderList=orderList1
        notifyDataSetChanged()
    }

    var totalpirce : Int?=null
    inner class OrderHolder(private val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(orderData: OrderData){
            binding.imgItem.load(orderData.orderImage){
                crossfade(true)
                placeholder(R.drawable.airmax)
            }
            binding.tvName.text= orderData.orderName
            binding.tvSubname.text = orderData.orderSubName
            binding.tvShoeSize.text = orderData.size
            binding.tvQuanNo.text = orderData.quatity
            totalpirce= (orderData.orderPrice).toInt() * (orderData.quatity).toInt()
            binding.tvPrice2.text = totalpirce.toString()

            binding.delete.setOnClickListener {
                orderViewModel.deleteOrder(orderData.orderId!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        return OrderHolder(
            ItemOrderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        holder.bind(orderList[position])
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

}