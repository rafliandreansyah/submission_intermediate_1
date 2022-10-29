package com.dicoding.submission_intermediate_1.ui.story.viewmodel

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submission_intermediate_1.api.ApiConfig
import com.dicoding.submission_intermediate_1.constant.PREF_TOKEN
import com.dicoding.submission_intermediate_1.model.ResponseDetailStory
import com.dicoding.submission_intermediate_1.model.ResponseGeneral
import com.dicoding.submission_intermediate_1.model.ResponseListStory
import com.dicoding.submission_intermediate_1.util.createPartFromString
import com.dicoding.submission_intermediate_1.util.prepareFilePart
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

@Suppress("DEPRECATION")
class StoryViewModel(application: Application) : AndroidViewModel(application) {

    val listStoryData: MutableLiveData<ResponseListStory> by lazy {
        MutableLiveData<ResponseListStory>()
    }

    val addStoryData: MutableLiveData<ResponseGeneral> by lazy {
        MutableLiveData<ResponseGeneral>()
    }

    val detailStoryData: MutableLiveData<ResponseDetailStory> by lazy {
        MutableLiveData<ResponseDetailStory>()
    }

    private val token = PreferenceManager.getDefaultSharedPreferences(application).getString(PREF_TOKEN, "")


    fun addStory(desc: String, file: File) {
        try {
            val bearer = "Bearer ${token as String}"
            val description = createPartFromString(desc)
            val storyFile = prepareFilePart("photo", file)
            ApiConfig.getApiService().addStory(bearer, description, storyFile).enqueue(object : Callback<ResponseGeneral>{
                override fun onResponse(
                    call: Call<ResponseGeneral>,
                    response: Response<ResponseGeneral>
                ) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<ResponseGeneral>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getAllStory() {
        try {
            val bearer = "Bearer ${token as String}"
            ApiConfig.getApiService().getListStory(bearer).enqueue(object : Callback<ResponseListStory>{
                override fun onResponse(
                    call: Call<ResponseListStory>,
                    response: Response<ResponseListStory>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()?.error == false) {
                            listStoryData.postValue(response.body())
                        }
                        else {
                            if (response.body()?.message != null && response.body()!!.message.isNotEmpty()) {
                                listStoryData.postValue(ResponseListStory(null, true, response.message()))
                            } else {
                                listStoryData.postValue(ResponseListStory(null, true, "error get data"))
                            }
                        }
                    } else if (response.code() == 401) {
                        listStoryData.postValue(ResponseListStory(null, true, "unauthorized"))
                    } else {
                        listStoryData.postValue(ResponseListStory(null, true, "error get data"))
                    }
                }

                override fun onFailure(call: Call<ResponseListStory>, t: Throwable) {
                    t.printStackTrace()
                    listStoryData.postValue(ResponseListStory(null, true, "error get data"))
                }

            })
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getDetailStory(id: String) {
        try {

            val bearer = "Bearer ${token as String}"

            ApiConfig.getApiService().getDetailStory(bearer, id).enqueue(object: Callback<ResponseDetailStory>{
                override fun onResponse(
                    call: Call<ResponseDetailStory>,
                    response: Response<ResponseDetailStory>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()?.error == false) {
                            detailStoryData.postValue(response.body())
                        }
                        else {
                            if (response.body()?.message != null && response.body()!!.message.isNotEmpty()) {
                                detailStoryData.postValue(ResponseDetailStory(true, response.message(), null))
                            } else {
                                detailStoryData.postValue(ResponseDetailStory(true, "error get data", null))
                            }
                        }
                    } else if (response.code() == 401) {
                        detailStoryData.postValue(ResponseDetailStory(true, "unauthorized", null))
                    } else {
                        detailStoryData.postValue(ResponseDetailStory(true, "error get data", null))
                    }
                }

                override fun onFailure(call: Call<ResponseDetailStory>, t: Throwable) {
                    detailStoryData.postValue(ResponseDetailStory(true, "error get data", null))
                }

            })

        }catch (e: Exception) {
            e.printStackTrace()
            detailStoryData.postValue(ResponseDetailStory(true, "error get data", null))
        }
    }

}