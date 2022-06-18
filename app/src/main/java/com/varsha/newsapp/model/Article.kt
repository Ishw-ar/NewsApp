package com.varsha.newsapp.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Article(
    @SerializedName("author")
    val author: @RawValue String?,
    @SerializedName("content")
    val content: @RawValue String?,
    @SerializedName("description")
    val description: @RawValue String?,
    @SerializedName("publishedAt")
    val publishedAt: @RawValue String?,
    @SerializedName("source")
    val source: @RawValue Source?,
    @SerializedName("title")
    val title: @RawValue String?,
    @SerializedName("url")
    val url: @RawValue String?,
    @SerializedName("urlToImage")
    val urlToImage: @RawValue String?,
    var isLike: @RawValue Boolean?
) : Parcelable