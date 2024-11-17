package com.alom.androidstudy2.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Request(
    @SerializedName("p_title")
    val title: String,
    @SerializedName("p_price")
    val price: String,
    @SerializedName("p_time")
    val time: String
) : Parcelable
