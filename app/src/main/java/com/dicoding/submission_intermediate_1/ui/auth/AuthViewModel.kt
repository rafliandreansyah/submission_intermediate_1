package com.dicoding.submission_intermediate_1.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submission_intermediate_1.api.ApiConfig
import com.dicoding.submission_intermediate_1.model.LoginResponse
import com.dicoding.submission_intermediate_1.model.RegisterResponse
import com.dicoding.submission_intermediate_1.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class AuthViewModel: ViewModel() {

    val loginData: MutableLiveData<LoginResponse> by lazy {
        MutableLiveData<LoginResponse>()
    }

    val registerData: MutableLiveData<RegisterResponse> by lazy {
        MutableLiveData<RegisterResponse>()
    }

    fun login(email: String, password: String) {
        try {
            val userLoginData = UserModel(email = email, password = password)
            val service = ApiConfig.getApiService().login(userLoginData)
            service.enqueue(object : Callback<LoginResponse>{
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        loginData.postValue(response.body())
                    }
                    else {
                        loginData.postValue(LoginResponse(error = true, message = "error get data"))
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    t.printStackTrace()
                    loginData.postValue(LoginResponse(error = true, message = "error get data"))
                }

            })
        }catch (e: Exception) {
            e.printStackTrace()
            loginData.postValue(LoginResponse(error = true, message = "error get data"))

        }

    }

    fun register(name: String, email: String, password:String) {
        try {
            val userRegisterData = UserModel(name, email, password)
            val service = ApiConfig.getApiService().register(userRegisterData)
            service.enqueue(object : Callback<RegisterResponse>{
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful) {
                        registerData.postValue(response.body())
                    }
                    else {
                        registerData.postValue(RegisterResponse(error = true, "error get data"))

                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    t.printStackTrace()
                    registerData.postValue(RegisterResponse(error = true, "error get data"))

                }

            })
        }catch (e: Exception) {
            e.printStackTrace()
            registerData.postValue(RegisterResponse(error = true, "error get data"))

        }
    }

}