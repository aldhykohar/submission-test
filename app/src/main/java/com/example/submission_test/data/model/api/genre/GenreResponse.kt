package com.example.submission_test.data.model.api.genre

import com.google.gson.annotations.SerializedName

data class GenreResponse(

	@field:SerializedName("genres")
	val genres: List<GenresItem?>? = null
)