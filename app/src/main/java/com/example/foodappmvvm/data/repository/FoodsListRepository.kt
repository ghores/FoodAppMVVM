package com.example.foodappmvvm.data.repository

import com.example.foodappmvvm.data.model.ResponseFoodList
import com.example.foodappmvvm.data.server.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class FoodsListRepository @Inject constructor(private val api: ApiServices) {
    suspend fun getFoodRandom(): Flow<Response<ResponseFoodList>> {
        return flow {
            emit(api.getFoodRandom())
        }.flowOn(Dispatchers.IO)
    }
}