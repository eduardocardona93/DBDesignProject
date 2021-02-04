package com.adworkshop.retrofit

import ClientListingResponse
import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.adworkshop.addclient.CreateClientRequestModel
import com.adworkshop.clients.ClientListingRequestModel
import com.adworkshop.login.LoginRequestModel
import com.adworkshop.signup.SignUpRequestModel
import com.drbconsumer.retrofit.RestCallback
import retrofit2.Call

/**
 * Created by Ranjana on 03-02-2021.
 */
class AdRepo {

    lateinit var activity: Activity


    /*
       Sign Up method request Method
    */
    fun signUp(signUpRequestModel: SignUpRequestModel): MutableLiveData<Any> {
        val apiResponse = MutableLiveData<Any>()
        val call = RetrofitServices.createService(APIServices::class.java)
            .signUp(signUpRequestModel)
        call.enqueue(RestProcess(object :
            RestCallback<ServiceMessageResponse> {
            override fun onFailure(call: Call<ServiceMessageResponse>, t: Throwable) {
            }

            override fun onError(error: String) {
                apiResponse.value = error
            }

            override fun onSuccess(
                call: Call<ServiceMessageResponse>,
                response: ServiceMessageResponse?
            ) {
                apiResponse.value = response
            }

        }))
        return apiResponse
    }

    fun login(loginRequestModel: LoginRequestModel): MutableLiveData<Any> {
        val apiResponse = MutableLiveData<Any>()
        val call = RetrofitServices.createService(APIServices::class.java)
            .login(loginRequestModel)
        call.enqueue(RestProcess(object :
            RestCallback<ServiceMessageResponse> {
            override fun onFailure(call: Call<ServiceMessageResponse>, t: Throwable) {
            }

            override fun onError(error: String) {
                apiResponse.value = error
            }

            override fun onSuccess(
                call: Call<ServiceMessageResponse>,
                response: ServiceMessageResponse?
            ) {
                apiResponse.value = response
            }

        }))
        return apiResponse
    }
    fun createClient(createClientRequestModel: CreateClientRequestModel): MutableLiveData<Any> {
        val apiResponse = MutableLiveData<Any>()
        val call = RetrofitServices.createService(APIServices::class.java)
            .createClient(createClientRequestModel)
        call.enqueue(RestProcess(object :
            RestCallback<ServiceMessageResponse> {
            override fun onFailure(call: Call<ServiceMessageResponse>, t: Throwable) {
            }

            override fun onError(error: String) {
                apiResponse.value = error
            }

            override fun onSuccess(
                call: Call<ServiceMessageResponse>,
                response: ServiceMessageResponse?
            ) {
                apiResponse.value = response
            }

        }))
        return apiResponse
    }
fun listClients(clientListingRequestModel: ClientListingRequestModel): MutableLiveData<Any> {
        val apiResponse = MutableLiveData<Any>()
        val call = RetrofitServices.createService(APIServices::class.java)
            .clientListing(clientListingRequestModel.CUST_ID)
        call.enqueue(RestProcess(object :
            RestCallback<ClientListingResponse> {
            override fun onFailure(call: Call<ClientListingResponse>, t: Throwable) {
            }

            override fun onError(error: String) {
                apiResponse.value = error
            }

            override fun onSuccess(
                call: Call<ClientListingResponse>,
                response: ClientListingResponse?
            ) {
                apiResponse.value = response
            }

        }))
        return apiResponse
    }
    fun deleteClient(clientListingRequestModel: ClientListingRequestModel): MutableLiveData<Any> {
        val apiResponse = MutableLiveData<Any>()
        val call = RetrofitServices.createService(APIServices::class.java)
            .deleteClient(clientListingRequestModel.CUST_ID)
        call.enqueue(RestProcess(object :
            RestCallback<ServiceMessageResponse> {
            override fun onFailure(call: Call<ServiceMessageResponse>, t: Throwable) {
            }

            override fun onError(error: String) {
                apiResponse.value = error
            }

            override fun onSuccess(
                call: Call<ServiceMessageResponse>,
                response: ServiceMessageResponse?
            ) {
                apiResponse.value = response
            }

        }))
        return apiResponse
    }

    companion object {
        private var adRepo: AdRepo? = null
        val instance: AdRepo?
            get() {
                if (adRepo == null) {
                    adRepo = AdRepo()
                }
                return adRepo
            }
    }
}