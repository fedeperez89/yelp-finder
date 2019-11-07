package com.xteam.sonytakehome.di

import android.content.Context
import com.xteam.sonytakehome.api.YelpService
import com.xteam.sonytakehome.mock
import com.xteam.sonytakehome.repository.DefaultBusinessRepository
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import retrofit2.Retrofit

class ApplicationModuleTest {

    private fun <T : Any> safeEq(value: T): T = eq(value) ?: value

    @Test
    fun provideAuthInterceptor() {
        val interceptor = ApplicationModule.provideAuthInterceptor()
        assertNotNull(interceptor)
        val chain: Interceptor.Chain = mock()
        val request: Request = mock()
        `when`(chain.request()).thenReturn(request)
        val requestBuilder: Request.Builder = mock()
        `when`(requestBuilder.addHeader(safeEq("Authorization"), anyString())).thenReturn(
            requestBuilder
        )
        val newRequest: Request = mock()
        `when`(requestBuilder.build()).thenReturn(newRequest)
        `when`(request.newBuilder()).thenReturn(requestBuilder)

        interceptor.intercept(chain);

        verify(chain).proceed(newRequest)
    }

    @Test
    fun provideRetrofit() {
        val okHttpClient = mock<OkHttpClient>()
        val retrofit = ApplicationModule.provideRetrofit(okHttpClient)

        assertNotNull(retrofit)
    }

    @Test
    fun provideBusinessRepo() {
        val defaultRepo = mock<DefaultBusinessRepository>()
        val repo = ApplicationModule.provideBusinessRepo(defaultRepo)

        assertEquals(defaultRepo, repo)
    }

    @Test
    fun provideCache() {
        val context = mock<Context>()
        ApplicationModule.provideCache(context)

        verify(context).cacheDir
    }

    @Test
    fun provideOkHttpClient() {
        val authInterceptor = mock<Interceptor>()
        val cacheInterceptor = mock<Interceptor>()
        val cache = mock<Cache>()

        val client = ApplicationModule.provideOkHttpClient(authInterceptor, cacheInterceptor, cache)

        assertNotNull(client)
    }

    @Test
    fun provideYelpService() {
        val retrofit = mock<Retrofit>()
        `when`(retrofit.create(YelpService::class.java)).thenReturn(mock())
        ApplicationModule.provideYelpService(retrofit)

        verify(retrofit).create(YelpService::class.java)
    }
}