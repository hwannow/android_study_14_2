package com.alom.androidstudy2.data

import com.google.gson.annotations.SerializedName

data class ResponseData(
    val result: Int,
    val message: String,
    val data: MutableList<Item>
)

data class Item(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("time")
    val time: String
)
