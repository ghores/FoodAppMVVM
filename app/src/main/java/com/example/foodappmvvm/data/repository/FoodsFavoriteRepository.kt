package com.example.foodappmvvm.data.repository

import com.example.foodappmvvm.data.database.FoodDao
import com.example.foodappmvvm.data.database.FoodEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodsFavoriteRepository @Inject constructor(private val dao: FoodDao) {
    fun getAllFoods(): Flow<MutableList<FoodEntity>> = dao.getAllFoods()
}