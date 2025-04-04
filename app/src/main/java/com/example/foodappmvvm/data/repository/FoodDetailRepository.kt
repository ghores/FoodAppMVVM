package com.example.foodappmvvm.data.repository

import com.example.foodappmvvm.data.database.FoodDao
import com.example.foodappmvvm.data.database.FoodEntity
import com.example.foodappmvvm.data.model.ResponseFoodList
import com.example.foodappmvvm.data.server.ApiServices
import com.example.foodappmvvm.utils.MyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FoodDetailRepository @Inject constructor(
    private val api: ApiServices,
    private val db: FoodDao
) {
    suspend fun getFoodDetail(id: Int): Flow<MyResponse<ResponseFoodList>> {
        return flow {
            emit(MyResponse.loading())
            when (api.getFoodDetails(id).code()) {
                in 200..202 -> {
                    emit(MyResponse.success(api.getFoodDetails(id).body()!!))
                }
            }
        }.catch {
            emit(MyResponse.error(it.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun saveFood(entity: FoodEntity) = db.saveFood(entity)
    suspend fun deleteFood(entity: FoodEntity) = db.deleteFood(entity)
    fun existsFood(id: Int) = db.existsFood(id)
}