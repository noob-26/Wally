package com.example.wally

import com.google.gson.annotations.SerializedName

data class ApiModel(@SerializedName("hits") val images: List<ImageDetails>)

data class ImageDetails(
    @SerializedName("largeImageURL")
    val url: String,
    val likes: Int,
    val user: String
)