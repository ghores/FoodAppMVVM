package com.example.foodappmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodappmvvm.data.database.FoodEntity
import com.example.foodappmvvm.data.model.ResponseFoodList
import com.example.foodappmvvm.data.repository.FoodDetailRepository
import com.example.foodappmvvm.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(private val repository: FoodDetailRepository) : ViewModel() {
    //Livedata
    val foodDetailData = MutableLiveData<MyResponse<ResponseFoodList>>()
    val isFavoriteData = MutableLiveData<Boolean>()

    fun getFoodDetails(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.getFoodDetail(id).collect {
            foodDetailData.postValue(it)
        }
    }

    fun saveNote(entity: FoodEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveFood(entity)
    }

    fun deleteNote(entity: FoodEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteFood(entity)
    }

    fun existsFood(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.existsFood(id).collect {
            isFavoriteData.postValue(it)
        }
    }
}