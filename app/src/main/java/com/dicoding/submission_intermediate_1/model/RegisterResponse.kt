package com.dicoding.submission_intermediate_1.model


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("error")
    var error: Boolean,
    @SerializedName("message")
    var message: String
)