package com.example.submission_test.di

import android.content.Context
import com.example.submission_test.BuildConfig
import com.example.submission_test.data.network.NetworkConnectionInterceptor
import com.example.submission_test.data.remote.ApiService
import com.example.submission_test.utils.UtilConstants.DEFAULT_LANG
import com.example.submission_test.utils.UtilConstants.PATH_API_KEY
import com.example.submission_test.utils.UtilConstants.PATH_DEFAULT_LANG
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by aldhykohar on 2/18/2022.
 */
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    @Provides
    fun providesNetworkConnectionInterceptor(@ApplicationContext context: Context): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(context)
    }

    @Provides
    fun providesOkHttpClient(logging: HttpLoggingInterceptor, networkConnectionInterceptor: NetworkConnectionInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter(PATH_API_KEY, BuildConfig.API_KEY)
                    .addQueryParameter(PATH_DEFAULT_LANG, DEFAULT_LANG)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .addInterceptor(logging)
            .addInterceptor(networkConnectionInterceptor)
            .build()
    }

    @Provides
    fun providesConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }

    @Provides
    fun providesRetrofit(baseUrl: String, converterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}