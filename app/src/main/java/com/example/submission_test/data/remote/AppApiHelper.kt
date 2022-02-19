package com.example.submission_test.data.remote

import com.example.submission_test.data.model.api.genre.GenreResponse
import com.example.submission_test.data.model.api.movie.MovieResponse
import javax.inject.Inject


/**
 * Created by aldhykohar on 2/18/2022.
 */
class AppApiHelper @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {
    override suspend fun getGenre(): GenreResponse = apiService.getGenre()
    override suspend fun getMoviesByGenre(page: Int?, genre: Int): MovieResponse =
        apiService.getMoviesByGenre(page, genre)

}