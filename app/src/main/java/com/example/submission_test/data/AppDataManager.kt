package com.example.submission_test.data

import com.example.submission_test.data.model.api.genre.GenreResponse
import com.example.submission_test.data.model.api.movie.MovieResponse
import com.example.submission_test.data.model.api.review.MovieReviewResponse
import com.example.submission_test.data.model.api.videos.MovieVideosResponse
import com.example.submission_test.data.remote.AppApiHelper
import javax.inject.Inject


/**
 * Created by aldhykohar on 2/18/2022.
 */
class AppDataManager @Inject constructor(
    private val api: AppApiHelper
) : DataManager {
    override suspend fun getGenre(): GenreResponse = api.getGenre()
    override suspend fun getMoviesByGenre(page: Int?, genre: Int): MovieResponse =
        api.getMoviesByGenre(page, genre)

    override suspend fun getMovieVideos(id: Int): MovieVideosResponse = api.getMovieVideos(id)
    override suspend fun getMovieReview(id: Int): MovieReviewResponse = api.getMovieReview(id)

}