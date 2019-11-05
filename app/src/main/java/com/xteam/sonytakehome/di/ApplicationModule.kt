package com.xteam.sonytakehome.di

import android.content.Context
import androidx.room.Room
import com.xteam.sonytakehome.BuildConfig
import com.xteam.sonytakehome.api.YelpService
import com.xteam.sonytakehome.db.BusinessDao
import com.xteam.sonytakehome.db.YelpDB
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object ApplicationModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideYelpService(): YelpService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(YelpService::class.java)

    @Singleton
    @Provides
    @JvmStatic
    fun provideDb(app: Context): YelpDB = Room
        .databaseBuilder(app, YelpDB::class.java, "takehome.db")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    @JvmStatic
    fun provideBusinessDao(db: YelpDB): BusinessDao = db.businessDao()

}