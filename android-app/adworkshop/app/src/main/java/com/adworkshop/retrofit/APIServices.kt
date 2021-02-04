package com.adworkshop.retrofit

import ClientListingResponse
import com.adworkshop.addclient.CreateClientRequestModel
import com.adworkshop.login.LoginRequestModel
import com.adworkshop.signup.SignUpRequestModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Path


public interface APIServices {
    @POST(API_Constants.LOGIN)
    fun login(@Body loginRequestModel: LoginRequestModel): Call<ServiceMessageResponse>

    @POST(API_Constants.SIGN_UP)
    fun signUp(@Body signUpRequestModel: SignUpRequestModel): Call<ServiceMessageResponse>

    @POST(API_Constants.CREATE_CLIENT)
    fun createClient(@Body createClientRequestModel: CreateClientRequestModel): Call<ServiceMessageResponse>

    /*  @GET(API_Constants.LIST_CLIENTS)
      fun clientListing(@Body clientListingRequestModel: ClientListingRequestModel): Call<ClientListingResponse>
  */

    @HTTP(method = "GET", path = API_Constants.LIST_CLIENTS + "/{id}", hasBody = false)
    fun clientListing(@Path("id") userId: Int): Call<ClientListingResponse>

    @HTTP(method = "DELETE", path = API_Constants.DELETE_CLIENT + "/{id}", hasBody = false)
    fun deleteClient(@Path("id") userId: Int): Call<ServiceMessageResponse>

    @HTTP(method = "POST", path = API_Constants.CREATE_CLIENT + "{id}", hasBody = true)
    fun updateClient(
        @Path("id") userId: Int,
        @Body createClientRequestModel: CreateClientRequestModel
    ): Call<ServiceMessageResponse>


}
