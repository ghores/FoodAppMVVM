package com.example.foodappmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodappmvvm.data.model.ResponseCategoriesList
import com.example.foodappmvvm.data.model.ResponseFoodList
import com.example.foodappmvvm.data.repository.FoodsListRepository
import com.example.foodappmvvm.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodsListViewModel @Inject constructor(private val repository: FoodsListRepository) : ViewModel() {
    //Livedata
    val randomFoodData = MutableLiveData<List<ResponseFoodList.Meal>>()
    val filtersListData = MutableLiveData<MutableList<Char>>()
    val categoriesListData = MutableLiveData<MyResponse<ResponseCategoriesList>>()
    val foodListData = MutableLiveData<MyResponse<ResponseFoodList>>()

    fun getFoodRandom() = viewModelScope.launch(Dispatchers.IO) {
        repository.getFoodRandom().collect {
            randomFoodData.postValue(it.body()?.meals!!)
        }
    }

    fun getFilterList() = viewModelScope.launch(Dispatchers.IO) {
        val letters = listOf('A'..'Z').flatten().toMutableList()
        filtersListData.postValue(letters)
    }

    fun getCategoriesFoodList() = viewModelScope.launch(Dispatchers.IO) {
        repository.getCategoriesFoodList().collect {
            categoriesListData.postValue(it)
        }
    }

    fun getFoodList(letter: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.getFoodList(letter).collect {
            foodListData.postValue(it)
        }
    }

    fun getSearchFoodList(letter: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.getSearchFoodList(letter).collect {
            foodListData.postValue(it)
        }
    }

    fun getFoodsByCategory(letter: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.getFoodsByCategory(letter).collect {
            foodListData.postValue(it)
        }
    }
}