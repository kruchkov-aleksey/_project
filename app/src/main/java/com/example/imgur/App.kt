package com.example.imgur

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.imgur.server.Api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    companion object {
        private var api: Api? = null
        fun getApi(): Api? {
            return api
        }
    }

    override fun onCreate() {
        super.onCreate()
        val client = OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(baseContext))
            .addInterceptor {
                val newBuilder = it.request().newBuilder()
                newBuilder.addHeader(
                    "Authorization",
                    "Bearer f4252748ee94632126ac2a003f58a429de0b939f"
                )
                it.proceed(newBuilder.build())
            }
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
    }
}