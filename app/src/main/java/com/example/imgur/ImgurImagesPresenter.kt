package com.example.imgur

import android.content.Context
import com.example.imgur.entity.BaseResponse
import moxy.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImgurImagesPresenter() : MvpPresenter<ImgurImagesView>() {
    fun getImgurImages(context: Context) {
        try {


            val call: Call<BaseResponse>? = ImgurImagesModel().getImgurImages();
            call?.enqueue(object : Callback<BaseResponse> {
                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    viewState.showError()
                }

                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>
                ) {
                    viewState.loadingImgurImages(response)
                }
            })
        }catch (t:Throwable){
            t.printStackTrace()
        }
    }
}