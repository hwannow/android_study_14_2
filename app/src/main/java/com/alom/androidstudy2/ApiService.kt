package com.alom.androidstudy2

import com.alom.androidstudy2.data.Request
import com.alom.androidstudy2.data.ResponseData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("rpc/add_item1")
    fun addItem (
        @Body body: Request
    ): Call<Request>

    @POST("rpc/get_item1")
    fun getItem(): Call<ResponseData>
}