package com.example.submission_test.data.remote

import com.example.submission_test.data.model.api.genre.GenreResponse


/**
 * Created by aldhykohar on 2/18/2022.
 */
interface ApiHelper {
    suspend fun getGenre(): GenreResponse
}