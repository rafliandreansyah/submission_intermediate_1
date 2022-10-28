package com.dicoding.submission_intermediate_1.ui.story

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submission_intermediate_1.api.ApiConfig
import com.dicoding.submission_intermediate_1.model.ResponseDetailStory
import com.dicoding.submission_intermediate_1.model.ResponseGeneral
import com.dicoding.submission_intermediate_1.model.ResponseListStory
import com.dicoding.submission_intermediate_1.util.createPartFromString
import com.dicoding.submission_intermediate_1.util.prepareFilePart
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class StoryViewModel : ViewModel() {

    val listStoryData: MutableLiveData<ResponseListStory> by lazy {
        MutableLiveData<ResponseListStory>()
    }

    val addStoryData: MutableLiveData<ResponseGeneral> by lazy {
        MutableLiveData<ResponseGeneral>()
    }

    val getDetailStory: MutableLiveData<ResponseDetailStory> by lazy {
        MutableLiveData<ResponseDetailStory>()
    }

    fun addStory(desc: String, file: File) {
        try {
            val bearer = ""
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
            val bearer = ""
            ApiConfig.getApiService().getListStory(bearer).enqueue(object : Callback<ResponseListStory>{
                override fun onResponse(
                    call: Call<ResponseListStory>,
                    response: Response<ResponseListStory>
                ) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<ResponseListStory>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getDetailStory(id: String) {
        try {

            val bearer = ""

            ApiConfig.getApiService().getDetailStory(bearer, id).enqueue(object: Callback<ResponseDetailStory>{
                override fun onResponse(
                    call: Call<ResponseDetailStory>,
                    response: Response<ResponseDetailStory>
                ) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<ResponseDetailStory>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

}