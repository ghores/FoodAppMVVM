package com.example.foodappmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodappmvvm.data.model.ResponseFoodList
import com.example.foodappmvvm.data.repository.FoodsListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodsListViewModel @Inject constructor(private val repository: FoodsListRepository) : ViewModel() {
    val randomFoodData = MutableLiveData<List<ResponseFoodList.Meal>>()
    val filtersListData = MutableLiveData<MutableList<Char>>()

    fun getFoodRandom() = viewModelScope.launch(Dispatchers.IO) {
        repository.getFoodRandom().collect {
            randomFoodData.postValue(it.body()?.meals!!)
        }
    }

    fun getFilterList() = viewModelScope.launch(Dispatchers.IO) {
        val letters = listOf('A'..'Z').flatten().toMutableList()
        filtersListData.postValue(letters)
    }
}