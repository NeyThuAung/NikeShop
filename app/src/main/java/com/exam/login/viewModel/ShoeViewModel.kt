package com.exam.login.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.exam.login.data.entity.ShoeData
import com.exam.login.data.repository.ShoeRepository
import com.exam.login.model.Shoe
import kotlinx.coroutines.launch

class ShoeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : ShoeRepository by lazy { ShoeRepository(application) }

    fun insertShoeForm(shoeData: ShoeData){
        viewModelScope.launch {
            repository.insertShoeData(shoeData)
        }
    }
    fun deleteAllShoes(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun searchDatabase(searchQuery : String) : LiveData<List<ShoeData>>{
        return repository.searchDatabase(searchQuery)
    }

    fun getShoeData(id : Int) : LiveData<ShoeData>{
        return repository.getShoeData(id)
    }
}