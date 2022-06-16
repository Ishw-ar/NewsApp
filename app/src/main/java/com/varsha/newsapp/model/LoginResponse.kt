package com.varsha.newsapp.model


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("token")
    val token: String?
)