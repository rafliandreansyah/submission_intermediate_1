package com.dicoding.submission_intermediate_1.model

import com.google.gson.annotations.SerializedName

data class ResponseDetailStory(

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("story")
	val story: Story? = null
)
