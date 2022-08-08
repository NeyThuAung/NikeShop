package com.exam.login.card.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.exam.login.ItemClickInterface
import com.exam.login.R
import com.exam.login.card.ListActivity
import com.exam.login.data.entity.ShoeData
import com.exam.login.databinding.ItemListBinding
import com.exam.login.filterActivity
import com.exam.login.login.LoginActivity
import com.exam.login.model.Shoe
import java.security.AlgorithmConstraints
import java.util.*
import kotlin.collections.ArrayList

class ShoeAdapter(
    //private val shoeList: ArrayList<Shoe>,

    private var shoeList1 : ArrayList<ShoeData>,
    private val listener: ItemClickInterface
    ): RecyclerView.Adapter<ShoeAdapter.ShoeHolder>() {

    fun setData(shoeList: ArrayList<ShoeData>){
        this.shoeList1=shoeList
        notifyDataSetChanged()
    }

    interface ItemClickInterface{
        fun onItemClicked(id:Int)
    }

    //private var shoeSearchList = ArrayList<Shoe>(shoeList)
    inner class ShoeHolder(private val binding: ItemListBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(shoeData: ShoeData){
            binding.imgItem.load(shoeData.imglink){
                crossfade(true)
                placeholder(R.drawable.airmax)
            }
            binding.saleLevel.text=shoeData.salelevel
            binding.tvShoeName.text=shoeData.name
            binding.tvShoeSub.text=shoeData.subname
            binding.tvShoePrice.text=shoeData.price

            binding.root.setOnClickListener {
                Log.i("ADAPTER","bind : $adapterPosition")
                listener.onItemClicked(shoeData.id!!)
                /*val intent = Intent(itemView.context,DetailActivity::class.java)
                intent.putExtra("shoeImg",shoe.img)
                intent.putExtra("saleLevel",shoe.saleLevel)
                intent.putExtra("",shoe.name)
                intent.putExtra("shoeshoeNameSub",shoe.subname)
                intent.putExtra("shoePrice",shoe.pirce)
                intent.putExtra("shoeDesc",shoe.desc)
                itemView.context.startActivity(intent)*/

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeHolder {
        return ShoeHolder(
            ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ShoeHolder, position: Int) {
        holder.bind(shoeList1[position])
    }

    override fun getItemCount(): Int {
       return shoeList1.size
    }



}