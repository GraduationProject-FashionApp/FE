package com.gradu.lookthat.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    /*@Provides
    @Singleton
    fun provideGaramgaebiLoginService(@NetworkModule.GaramgaebiLoginRetrofit retrofit: Retrofit) : GaramgaebiLoginService =
        retrofit.create(GaramgaebiLoginService::class.java)

    @Provides
    @Singleton
    fun provideGaramgaebiService(@NetworkModule.GaramgaebiRetrofit retrofit: Retrofit) : GaramgaebiService =
        retrofit.create(GaramgaebiService::class.java)*/
}