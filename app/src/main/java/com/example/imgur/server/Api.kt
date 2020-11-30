package com.example.imgur.server

import com.example.imgur.entity.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface Api {
    companion object {
        val BASE_URL: String = "https://api.imgur.com/"
    }

    @GET("3/gallery/top/day/{page}")
    fun getImgurImages(@Path("page") page: Int = 0): Call<BaseResponse>?
}
