package com.example.foodappmvvm.data.repository

import com.example.foodappmvvm.data.model.ResponseCategoriesList
import com.example.foodappmvvm.data.model.ResponseFoodList
import com.example.foodappmvvm.data.server.ApiServices
import com.example.foodappmvvm.utils.MyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
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
        }.catch {
            emit(MyResponse.error(it.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFoodList(letter: String): Flow<MyResponse<ResponseFoodList>> {
        return flow {
            emit(MyResponse.loading())
            when (api.getFoodList(letter).code()) {
                in 200..202 -> {
                    emit(MyResponse.success(api.getFoodList(letter).body()!!))
                }
            }
        }.catch {
            emit(MyResponse.error(it.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearchFoodList(letter: String): Flow<MyResponse<ResponseFoodList>> {
        return flow {
            emit(MyResponse.loading())
            when (api.getSearchFoodList(letter).code()) {
                in 200..202 -> {
                    emit(MyResponse.success(api.getSearchFoodList(letter).body()!!))
                }
            }
        }.catch {
            emit(MyResponse.error(it.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFoodsByCategory(letter: String): Flow<MyResponse<ResponseFoodList>> {
        return flow {
            emit(MyResponse.loading())
            when (api.getFoodsByCategory(letter).code()) {
                in 200..202 -> {
                    emit(MyResponse.success(api.getFoodsByCategory(letter).body()!!))
                }
            }
        }.catch {
            emit(MyResponse.error(it.message.toString()))
        }.flowOn(Dispatchers.IO)
    }
}