package com.example.submission_test.data.remote

import com.example.submission_test.data.model.api.genre.GenreResponse
import com.example.submission_test.data.model.api.movie.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by aldhykohar on 2/18/2022.
 */
interface ApiService {
    @GET("genre/movie/list")
    suspend fun getGenre(): GenreResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("page")
        pageNumber: Int? = 1,
        @Query("with_genres")
        genre: Int,
    ): MovieResponse
}