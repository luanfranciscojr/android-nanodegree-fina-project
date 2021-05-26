package com.example.twitteranalyzer.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

enum class ApiStatus { LOADING, ERROR, DONE }

val networkModule = module {
    val connectTimeout: Long = 40
    val readTimeout: Long = 40

    fun provideHttpClient(token: String): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, SECONDS)
            .readTimeout(readTimeout, SECONDS)
        okHttpClientBuilder.addInterceptor { chain ->
            var original = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
            requestBuilder.addHeader(
                "Authorization",
                token
            )
            original = requestBuilder.build()
            chain.proceed(original)
        }
        return okHttpClientBuilder.build()
    }

    fun provideMoshi(): Moshi {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
        return moshi.build()
    }

    fun provideRetrofit(client: OkHttpClient, baseUrl: String, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    factory { parameters ->
        provideHttpClient(parameters.values[0] as String)
    }
    single { provideMoshi() }
    factory { parameters ->
        provideRetrofit(
            get(parameters = { parametersOf(parameters.values[1] as String) }),
            parameters.values[0] as String,
            get()
        )
    }
}