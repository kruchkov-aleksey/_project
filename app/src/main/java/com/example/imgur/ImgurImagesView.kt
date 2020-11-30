package com.example.imgur

import com.example.imgur.entity.BaseResponse
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import retrofit2.Response

interface ImgurImagesView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun loadingImgurImages(response: Response<BaseResponse>)
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError()
}