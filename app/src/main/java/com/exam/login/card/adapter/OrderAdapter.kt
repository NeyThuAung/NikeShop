package com.exam.login.card.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.exam.login.R
import com.exam.login.data.entity.OrderData
import com.exam.login.data.entity.ShoeData
import com.exam.login.databinding.ItemOrderBinding

class OrderAdapter(

    private var orderList : ArrayList<OrderData>

        ): RecyclerView.Adapter<OrderAdapter.OrderHolder>() {

    fun setData(orderList1: ArrayList<OrderData>){
        this.orderList=orderList1
        notifyDataSetChanged()
    }

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
            binding.tvPrice.text = orderData.orderPrice
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