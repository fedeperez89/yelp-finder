package com.xteam.sonytakehome.di

import android.content.Context
import android.util.Log
import com.xteam.sonytakehome.BuildConfig
import com.xteam.sonytakehome.api.YelpService
import com.xteam.sonytakehome.repository.BusinessRepository
import com.xteam.sonytakehome.repository.DefaultBusinessRepository
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
object ApplicationModule {

    @Provides
    @Singleton
    @JvmStatic
    @Named("auth")
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newRequest = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer ${BuildConfig.YELP_API_KEY}")
                .build()

            Log.d("Netowrk", newRequest.url.toString())

            val result = chain.proceed(newRequest)

            Log.d("Network result", "Result ${result.code}")
            result
        }
    }

    @Provides
    @Singleton
    @JvmStatic
    @Named("cached")
    fun provideCacheInterceptor(): Interceptor {
        return Interceptor { chain ->

            val response = chain.proceed(chain.request())

            val cacheControl = CacheControl.Builder()
                .maxAge(15, TimeUnit.MINUTES)
                .build()

            response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    @Singleton
    @JvmStatic
    fun provideBusinessRepo(repo: DefaultBusinessRepository): BusinessRepository = repo


    @Provides
    @Singleton
    @JvmStatic
    fun provideCache(context: Context): Cache {
        val httpCacheDirectory = File(context.cacheDir, "yelpCache")
        val cacheSize = 10L * 1024L * 1024L
        return Cache(httpCacheDirectory, cacheSize)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient(
        @Named("cached") cacheInterceptor: Interceptor, @Named("auth") authInterceptor: Interceptor,
        cache: Cache
    ) = OkHttpClient().newBuilder()
        .cache(cache)
        .addNetworkInterceptor(cacheInterceptor)
        .addInterceptor(authInterceptor)
        .build()

    @Provides
    @Singleton
    @JvmStatic
    fun provideYelpService(retrofit: Retrofit): YelpService = retrofit
        .create(YelpService::class.java)

}