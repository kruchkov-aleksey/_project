package com.example.imgur.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImgurImage(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("width")
    val width: Int? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("size")
    val size: Int? = null,
    @SerializedName("views")
    val views: Int? = null,
    @SerializedName("ups")
    val ups: Int? = null,
    @SerializedName("comment_count")
    val comment_count: Int? = null,
    @SerializedName("link")
    val link: String? = null,
    @SerializedName("score")
    val score: Int? = null,
    @SerializedName("images")
    val images: List<Image>? = null,
    @SerializedName("tags")
    val tags: List<Tag>? = null
) : Parcelable

@Parcelize
data class Image(
    @SerializedName("width")
    val width: Int? = null,

    @SerializedName("height")
    val height: Int? = null,

    @SerializedName("link")
    val link: String? = null,

    ) : Parcelable

@Parcelize
data class Tag(
    @SerializedName("description")
    val description: String? = null
) : Parcelable