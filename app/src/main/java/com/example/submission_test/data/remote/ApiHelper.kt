package com.example.submission_test.data.remote

import com.example.submission_test.data.model.api.genre.GenreResponse
import com.example.submission_test.data.model.api.movie.MovieResponse


/**
 * Created by aldhykohar on 2/18/2022.
 */
interface ApiHelper {
    suspend fun getGenre(): GenreResponse
    suspend fun getMoviesByGenre(page: Int?, genre: Int): MovieResponse
}