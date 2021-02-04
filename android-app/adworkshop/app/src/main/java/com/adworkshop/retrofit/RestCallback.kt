package com.drbconsumer.retrofit

import retrofit2.Call

interface RestCallback<T> {
    fun onFailure(call:Call<T>,t:Throwable)
    fun onSuccess(call:Call<T>,response:T?)
    fun onError(error:String)
}