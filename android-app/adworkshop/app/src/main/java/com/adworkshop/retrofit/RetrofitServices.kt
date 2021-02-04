package com.adworkshop.retrofit

import android.util.Log

import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object RetrofitServices {
    private val retrofit = Retrofit.Builder()
        .baseUrl(API_Constants.BASE_URL).client(provideClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S> createService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass)
    }

    private fun provideClient():OkHttpClient {
        val builder=OkHttpClient.Builder()
        builder.connectTimeout(500,TimeUnit.MINUTES)
        builder.readTimeout(500,TimeUnit.MINUTES)
        builder.interceptors().add(Interceptor { chain->
            var request=chain.request()
            val url=request.url.newBuilder().build()
            val tz=TimeZone.getDefault()
            Log.e("hit url:","$url//")
            request=request.newBuilder()
                .url(url).build()
            request=
                request.newBuilder()
                 /*   .addHeader("email","ranjana@yopmail.com")
                    .addHeader("password","123456")*/
                    .url(url).build()

            chain.proceed(request)
        })
        val logging=HttpLoggingInterceptor()
        logging.level=HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(logging)
        val dispatcher=Dispatcher()
        dispatcher.maxRequests=5
        builder.dispatcher(dispatcher)
        return builder.build()
    }
}