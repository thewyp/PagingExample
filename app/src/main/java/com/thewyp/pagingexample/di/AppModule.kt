package com.thewyp.pagingexample.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.thewyp.pagingexample.BuildConfig
import com.thewyp.pagingexample.api.CatApi
import com.thewyp.pagingexample.data.db.CatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideMoshi() = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideInterceptor() = Interceptor {
        it.proceed(
            it.request().newBuilder().addHeader(AUTH_HEADER, API_KEY).build()
        )
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttp(
        interceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(httpLoggingInterceptor)
        .callTimeout(10, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun provideCatApi(retrofit: Retrofit) = retrofit.create(CatApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): CatDatabase {
        return Room.databaseBuilder(
            appContext, CatDatabase::class.java,
            "cats.db"
        ).build()
    }


    companion object {
        const val API_KEY = BuildConfig.CAT_API_KEY
        const val BASE_URL = "https://api.thecatapi.com"
        const val AUTH_HEADER = "x-api-key"
    }

}