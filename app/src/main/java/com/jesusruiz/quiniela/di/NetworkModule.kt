package com.jesusruiz.quiniela.di

import com.jesusruiz.quiniela.data.datasource.ResultsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val oneFootballApi = "v3.football.api-sports.io/"
    private const val api_key = "2070bfc4055618d729a9c49a3c5fc94d"

    @Provides
    @Singleton
    fun provideOneFootballHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("x-api-sports-key",api_key)
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideOneFootballRetrofit(okHttpClient: OkHttpClient): Retrofit{
            return Retrofit.Builder()
                .baseUrl(oneFootballApi)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}