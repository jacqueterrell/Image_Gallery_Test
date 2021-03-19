package com.jacque.imagegallerytest.di

import com.jacque.imagegallerytest.network.ImgurApiEndpoints
import com.jacque.imagegallerytest.ui.albums.AlbumsRepository
import com.jacque.imagegallerytest.ui.albums.AlbumsViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Our Koin Module object
 */
object Modules {

    private const val BASE_URL = "https://api.imgur.com/3/gallery/"

    val repoModule = module {
        single { AlbumsRepository(get()) }
    }

    val viewModelModule = module {
        viewModel { AlbumsViewModel(get()) }
    }

    val networkModule = module {
        single { provideOkHttpClient() }
        single { provideRetrofit(get()) }
        single { provideApiEndpoints(get()) }
    }

    private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private fun provideApiEndpoints(retrofit: Retrofit): ImgurApiEndpoints {
        return retrofit.create(ImgurApiEndpoints::class.java)
    }
}