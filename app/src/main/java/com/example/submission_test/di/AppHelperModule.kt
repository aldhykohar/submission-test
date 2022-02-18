package com.example.submission_test.di

import android.content.SharedPreferences
import com.example.submission_test.data.AppDataManager
import com.example.submission_test.data.remote.ApiService
import com.example.submission_test.data.remote.AppApiHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * Created by aldhykohar on 2/18/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppHelperModule {


    @Provides
    fun providesAppApiHelper(apiService: ApiService) = AppApiHelper(apiService)

    @Provides
    fun providesAppDataManager(appApiHelper: AppApiHelper): AppDataManager {
        return AppDataManager(appApiHelper)
    }
}