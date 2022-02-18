package com.example.submission_test.data

import com.example.submission_test.data.model.api.genre.GenreResponse
import com.example.submission_test.data.remote.AppApiHelper
import javax.inject.Inject


/**
 * Created by aldhykohar on 2/18/2022.
 */
class AppDataManager @Inject constructor(
    private val api: AppApiHelper
) : DataManager {
    override suspend fun getGenre(): GenreResponse = api.getGenre()

}