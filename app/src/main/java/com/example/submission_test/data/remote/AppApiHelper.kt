package com.example.submission_test.data.remote

import javax.inject.Inject


/**
 * Created by aldhykohar on 2/18/2022.
 */
class AppApiHelper @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {

//    override suspend fun getMoviesApiCall() = apiService.getMovies()

}