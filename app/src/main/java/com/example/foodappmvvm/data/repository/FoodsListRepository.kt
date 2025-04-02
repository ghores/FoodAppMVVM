package com.example.foodappmvvm.data.repository

import com.example.foodappmvvm.data.model.ResponseCategoriesList
import com.example.foodappmvvm.data.model.ResponseFoodList
import com.example.foodappmvvm.data.server.ApiServices
import com.example.foodappmvvm.utils.MyResponse
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

    suspend fun getCategoriesFoodList(): Flow<MyResponse<ResponseCategoriesList>> {
        return flow {
            emit(MyResponse.loading())
            //Response
            when (api.getCategoriesFoodList().code()) {
                in 200..202 -> {
                    emit(MyResponse.success(api.getCategoriesFoodList().body()!!))
                }

                422 -> {
                    emit(MyResponse.error(""))
                }

                in 400..499 -> {
                    emit(MyResponse.error(""))
                }

                in 500..599 -> {
                    emit(MyResponse.error(""))
                }
            }
        }
    }
}