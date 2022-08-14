package com.exam.login.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.exam.login.data.entity.OrderData
import com.exam.login.data.repository.OrderRepository
import kotlinx.coroutines.launch

class OrderViewModel(application: Application) : AndroidViewModel(application) {

    private val orderRepository : OrderRepository by lazy { OrderRepository(application) }

    fun insertOrder(orderData: OrderData){
        viewModelScope.launch {
            orderRepository.insertOrderData(orderData)
        }
    }

    fun getOrderData() : LiveData<List<OrderData>>{
        return orderRepository.getOrderData()
    }

    fun deleteAllOrders(){
        viewModelScope.launch {
            orderRepository.deleteAll()
        }
    }

    fun deleteOrder(id :Int){
        viewModelScope.launch {
            orderRepository.deleteOrder(id)
        }
    }
}