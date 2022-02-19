package com.example.submission_test.data.model.api.videos

import com.google.gson.annotations.SerializedName

data class MovieVideosResponse(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("results")
	val results: List<VideosItem?>? = null
)