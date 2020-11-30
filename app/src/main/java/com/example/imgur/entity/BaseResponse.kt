package com.example.imgur.entity

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("data")
    var all: List<ImgurImage>? = null
)