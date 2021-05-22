package com.thewyp.pagingexample.api

import com.thewyp.pagingexample.data.model.Cat
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    @GET("v1/images/search")
    suspend fun getCatImages(
        @Query("limit") size: Int,
        @Query("order") order: String = "Asc",
        @Query("page") page: Int
    ): List<Cat>
}