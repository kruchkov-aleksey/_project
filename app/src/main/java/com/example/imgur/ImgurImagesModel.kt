package com.example.imgur

import android.content.Context

class ImgurImagesModel (){
    fun getImgurImages() = App.getApi()?.getImgurImages()
}