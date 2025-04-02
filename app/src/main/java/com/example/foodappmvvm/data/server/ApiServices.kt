package com.example.foodappmvvm.data.server

import com.example.foodappmvvm.data.model.ResponseCategoriesList
import com.example.foodappmvvm.data.model.ResponseFoodList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("random.php")
    suspend fun getFoodRandom(): Response<ResponseFoodList>

    @GET("categories.php")
    suspend fun getCategoriesFoodList(): Response<ResponseCategoriesList>

    @GET("search.php")
    suspend fun getFoodList(@Query("f") letter: String): Response<ResponseFoodList>

    @GET("search.php")
    suspend fun getSearchFoodList(@Query("s") letter: String): Response<ResponseFoodList>

    @GET("filter.php")
    suspend fun getFoodsByCategory(@Query("c") letter: String): Response<ResponseFoodList>

    @GET("lookup.php")
    suspend fun getFoodDetails(@Query("i") id: Int): Response<ResponseFoodList>
}
