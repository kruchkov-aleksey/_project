package com.example.imgur

import android.content.ClipData
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.imgur.entity.BaseResponse
import com.example.imgur.entity.ImgurImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemPageKeyedDataSource(private val model: ImgurImagesModel) :
    PageKeyedDataSource<Int, ImgurImage>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ImgurImage>
    ) {
        try {


            val call: Call<BaseResponse>? = model.getImgurImages();
            call?.enqueue(object : Callback<BaseResponse> {
                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Log.e("DataSource", "Fail, loadInitial")
                }

                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>
                ) {
                    callback.onResult(response.body()?.all ?: listOf(), null, 1)
                }
            })
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ImgurImage>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ImgurImage>) {
        try {


            val call: Call<BaseResponse>? = model.getImgurImages();
            call?.enqueue(object : Callback<BaseResponse> {
                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Log.e("DataSource", "Fail, loadAfter")
                }

                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>
                ) {
                    callback.onResult(response.body()?.all ?: listOf(), params.key + 1)
                }
            })
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }
}