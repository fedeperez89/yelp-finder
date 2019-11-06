package com.xteam.sonytakehome.di

import com.xteam.sonytakehome.api.TestYelpService
import com.xteam.sonytakehome.api.YelpService
import com.xteam.sonytakehome.repository.BusinessRepository
import com.xteam.sonytakehome.repository.DefaultBusinessRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object TestApplicationModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideBusinessRepo(repo: DefaultBusinessRepository): BusinessRepository = repo

    @Provides
    @Singleton
    @JvmStatic
    fun provideYelpService(): YelpService = TestYelpService()
}
