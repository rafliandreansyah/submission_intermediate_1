package com.dicoding.submission_intermediate_1.model

import com.google.gson.annotations.SerializedName

data class ResponseListStory(

	@field:SerializedName("listStory")
	val listStory: List<Story>?,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,
)
