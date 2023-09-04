package com.gradu.lookthat.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

//    @Provides
//    @Singleton
//    fun providesLoginRepository(loginDataSource: LoginDataSource) : LoginRepository =
//        LoginRepositoryImpl(loginDataSource)
//
//    @Provides
//    @Singleton
//    fun providesHomeRepository(homeDataSource: HomeDataSource) : HomeRepository =
//        HomeRepositoryImpl(homeDataSource)
//
//    @Provides
//    @Singleton
//    fun providesProfileRepository(profileDataSource: ProfileDataSource) : ProfileRepository =
//        ProfileRepositoryImpl(profileDataSource)
}