package com.adworkshop.retrofit

import android.app.Activity
import com.drbconsumer.retrofit.RestCallback


import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException


class RestProcess<T>(var callback: RestCallback<T>):
    Callback<T> {


    override fun onResponse(call:Call<T>,response:Response<T>) {
        if(response!=null) {
            when {
                response.code() == 422-> {
                    val obj=JSONObject(response.errorBody()?.string()?.let { ApiException(it) }!!.message)
                    val message=obj.getString("message")
                    callback.onError(message)

                }
                response.code() == 401-> {
                    //Unauthorized
                    val obj=JSONObject(response.errorBody()?.string()?.let { ApiException(it) }!!.message)
                    val message=obj.getString("message")
                    callback.onError(message)
                }
                else-> {
                    callback.onSuccess(call,response.body())
                }
            }

        }else{
            callback.onError("Something went wrong")

        }
    }

    override fun onFailure(call:Call<T>,t:Throwable) {
        if (t is UnknownHostException){
            callback.onError("Please check your Internet connection")
        }else {
            callback.onError(t.localizedMessage)
        }
    }



    class ApiException(message:String):JSONException(message)
}