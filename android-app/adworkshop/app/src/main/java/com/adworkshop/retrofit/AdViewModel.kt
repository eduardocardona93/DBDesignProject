package com.adworkshop.retrofit

import androidx.lifecycle.MutableLiveData
import com.adworkshop.addclient.CreateClientRequestModel
import com.adworkshop.clients.ClientListingRequestModel
import com.adworkshop.login.LoginRequestModel
import com.adworkshop.signup.SignUpRequestModel

/**
 * Created by Ranjana on 03-02-2021.
 */
class AdViewModel {

    private var adRepo: AdRepo? = null

    init {
        adRepo = AdRepo.instance
    }

    //SignUp Request Method
    fun signUp(signUpRequestModel: SignUpRequestModel): MutableLiveData<Any> {
        return adRepo!!.signUp(signUpRequestModel)
    }

    //Login Request Method
    fun login(loginRequestModel: LoginRequestModel): MutableLiveData<Any> {
        return adRepo!!.login(loginRequestModel)
    }

    //Create New Client Request Method
    fun createClient(createClientRequestModel: CreateClientRequestModel): MutableLiveData<Any> {
        return adRepo!!.createClient(createClientRequestModel)
    }
    //Update existing Client Request Method
    fun updateClient(createClientRequestModel: CreateClientRequestModel): MutableLiveData<Any> {
        return adRepo!!.updateClient(createClientRequestModel)
    }
    //List Clients for logged in Sales Managers Request Method
    fun clientListing(clientListingRequestModel: ClientListingRequestModel): MutableLiveData<Any> {
        return adRepo!!.listClients(clientListingRequestModel)
    }

    //Delete client from list Request Method
    fun deleteClient(clientListingRequestModel: ClientListingRequestModel): MutableLiveData<Any> {
        return adRepo!!.deleteClient(clientListingRequestModel)
    }
}