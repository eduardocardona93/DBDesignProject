package com.adworkshop.retrofit

import ClientData

object API_Constants {

    /*Public URl
    * */
    const val BASE_URL = "https://dbdesign-backend.herokuapp.com/api/v1/"

    //Global Variable
    lateinit var USER_DATA: Data
    lateinit var CLIENT_DATA: ClientData
    var EDIT_CLIENT: Boolean = false

    /*Endpoints
    * */
    const val LOGIN = "login"
    const val SIGN_UP = "salesPerson/"
    const val CREATE_CLIENT = "clients/"
    const val LIST_CLIENTS = "salesPerson_clients"
    const val DELETE_CLIENT = "clients"


}