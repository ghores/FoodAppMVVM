package com.example.foodappmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodappmvvm.data.database.FoodEntity
import com.example.foodappmvvm.data.repository.FoodsFavoriteRepository
import com.example.foodappmvvm.utils.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodsFavoriteViewModel @Inject constructor(private val repository: FoodsFavoriteRepository) : ViewModel() {
    //Livedata
    val favoritesListData = MutableLiveData<DataStatus<MutableList<FoodEntity>>>()

    fun getAllFoods() = viewModelScope.launch(Dispatchers.IO) {
        repository.getAllFoods().collect {
            favoritesListData.postValue(DataStatus.success(it, it.isEmpty()))
        }
    }
}