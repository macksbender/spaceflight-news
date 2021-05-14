package com.macksbender.spaceflight.di

import com.macksbender.spaceflight.data.remote.SpaceFlightAPI
import com.macksbender.spaceflight.repository.MainRepository
import com.macksbender.spaceflight.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        api: SpaceFlightAPI
    ) = MainRepository(api)

    @Singleton
    @Provides
    fun provideSpaceFlightAPI(): SpaceFlightAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(SpaceFlightAPI::class.java)
    }

}