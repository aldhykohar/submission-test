package com.example.submission_test.data.remote

import com.example.submission_test.data.model.api.genre.GenreResponse
import retrofit2.http.GET


/**
 * Created by aldhykohar on 2/18/2022.
 */
interface ApiService {
    @GET("genre/movie/list")
    suspend fun getGenre(): GenreResponse
}