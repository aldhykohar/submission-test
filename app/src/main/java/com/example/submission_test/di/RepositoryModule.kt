package com.example.submission_test.di

import com.example.submission_test.data.AppDataManager
import com.example.submission_test.data.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * Created by aldhykohar on 2/18/2022.
 */
@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideDataRepository(appDataManager: AppDataManager): DataRepository {
        return DataRepository(appDataManager)
    }
}