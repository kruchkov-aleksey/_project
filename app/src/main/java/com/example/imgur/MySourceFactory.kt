package com.example.imgur

import com.example.imgur.entity.ImgurImage
import javax.sql.DataSource

class MySourceFactory: androidx.paging.DataSource.Factory<Int, ImgurImage>() {
    override fun create(): androidx.paging.DataSource<Int, ImgurImage> {
        return ItemPageKeyedDataSource(ImgurImagesModel())
    }
}