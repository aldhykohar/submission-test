package com.example.submission_test.data

import com.example.submission_test.base.BaseRepository
import javax.inject.Inject


/**
 * Created by aldhykohar on 2/18/2022.
 */
class DataRepository @Inject constructor(
    private val appDataManager: AppDataManager
) : BaseRepository() {

    suspend fun getGenre() = safeApiCall {
        appDataManager.getGenre()
    }

    suspend fun getMoviesByGenre(page: Int?, genre: Int) = safeApiCall {
        appDataManager.getMoviesByGenre(page, genre)
    }

    suspend fun getMovieVideos(id: Int) = safeApiCall {
        appDataManager.getMovieVideos(id)
    }

    suspend fun getMovieReview(id: Int) = safeApiCall {
        appDataManager.getMovieReview(id)
    }

}
