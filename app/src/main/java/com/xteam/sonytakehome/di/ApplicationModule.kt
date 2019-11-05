package com.xteam.sonytakehome.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.xteam.sonytakehome.BuildConfig
import com.xteam.sonytakehome.api.YelpService
import com.xteam.sonytakehome.db.BusinessDao
import com.xteam.sonytakehome.db.YelpDB
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object ApplicationModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newRequest = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer ${BuildConfig.YELP_API_KEY}")
                .build()

            Log.d("Netowrk", newRequest.url.toString())

            chain.proceed(newRequest)
        }
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient(interceptor: Interceptor) = OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    @JvmStatic
    fun provideYelpService(retrofit: Retrofit): YelpService = retrofit
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