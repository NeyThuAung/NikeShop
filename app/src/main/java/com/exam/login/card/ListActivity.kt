package com.exam.login.card

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import com.exam.login.R
import com.exam.login.card.adapter.ShoeAdapter
import com.exam.login.data.entity.ShoeData

import com.exam.login.databinding.ActivityListBinding
import com.exam.login.detail.DetailActivity
import com.exam.login.viewModel.ShoeViewModel
import kotlin.collections.ArrayList

class ListActivity : AppCompatActivity(),ShoeAdapter.ItemClickInterface {
    private lateinit var binding: ActivityListBinding
    private lateinit var shoeAdapter: ShoeAdapter
    private val viewModel : ShoeViewModel by viewModels()
    private lateinit var shoeList : ArrayList<ShoeData>
    //val displayList= ArrayList<Shoe>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_list)
       // displayList.addAll(TempData.shoeList)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        shoeList = arrayListOf()

        initRec()
        //getStudent("")
        searchDatabase("")
        binding.sampleEditText.doAfterTextChanged {
            if(it.toString().isNullOrEmpty()){
                searchDatabase("")
            }else
                 searchDatabase(it.toString().trim())
            //shoeAdapter.filter.filter(it.toString())
        }


        findViewById<ImageView>(R.id.ic_filter).setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val menuItem=menu!!.findItem(R.id.search)

        if (menuItem!=null){
            val searchView = menuItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(nextText: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }

            })
        }


        return super.onCreateOptionsMenu(menu)
    }

    private fun initRec(){
        shoeAdapter = ShoeAdapter(shoeList,this)
        binding.recList.also {
            it.setHasFixedSize(true)

            it.layoutManager= GridLayoutManager(this@ListActivity,2)
            it.adapter = shoeAdapter
        }
    }

   /* @SuppressLint("NotifyDataSetChanged")
    private fun getStudent(s: String) {
        viewModel.getShoeData(s).observe(this){
            if (it.isNotEmpty()){
                shoeList = it as ArrayList<ShoeData> *//* = java.util.ArrayList<com.exam.login.data.entity.ShoeData> *//*
                shoeAdapter.setData(shoeList)
            }
        }
    }*/

    private fun searchDatabase(query : String){
        viewModel.searchDatabase(query).observe(this){
            if (it.isNotEmpty()){
                shoeList= it as ArrayList<ShoeData>
                shoeAdapter.setData(shoeList)
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }


    override fun onItemClicked(id: Int) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("shoeId",id)
        startActivity(intent)
    }
}
