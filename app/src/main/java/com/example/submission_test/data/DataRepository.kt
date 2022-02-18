package com.example.submission_test.data

import com.example.submission_test.base.BaseRepository
import javax.inject.Inject


/**
 * Created by aldhykohar on 2/18/2022.
 */
class DataRepository @Inject constructor(
    private val appDataManager: AppDataManager
) : BaseRepository() {

    /** Remote Data - Fetch API **/
    /*suspend fun getMoviesApiCall() = safeApiCall {
        appDataManager.getMoviesApiCall()
    }*/

}
