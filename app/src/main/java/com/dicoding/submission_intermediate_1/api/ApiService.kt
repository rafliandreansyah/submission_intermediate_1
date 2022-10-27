package com.dicoding.submission_intermediate_1.api

import com.dicoding.submission_intermediate_1.model.LoginResponse
import com.dicoding.submission_intermediate_1.model.RegisterResponse
import com.dicoding.submission_intermediate_1.model.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Content-Type: application/json", "X-Requested-With: XMLHttpRequest")
    @POST("login")
    fun login(@Body user: UserModel): Call<LoginResponse>

    @POST("register")
    fun register(@Body user: UserModel): Call<RegisterResponse>

}