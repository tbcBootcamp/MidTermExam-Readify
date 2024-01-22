package com.example.midtermexam.di

import android.content.Context
import androidx.paging.PagingConfig
import com.example.midtermexam.BuildConfig
import com.example.midtermexam.data.global.service.BooksApi
import com.example.midtermexam.data.global.util.addLoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun getContext(@ApplicationContext context: Context): Context = context

//    @Provides
//    @Singleton
//    fun getConfig(): PagingConfig = PagingConfig(10)

    @Provides
    @Singleton
    fun provideBooksApi(retrofit: Retrofit): BooksApi =
        retrofit.create(BooksApi::class.java)

    @Provides
    @Singleton
    fun provideOkHTTPClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .addLoggingInterceptor(context)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
}