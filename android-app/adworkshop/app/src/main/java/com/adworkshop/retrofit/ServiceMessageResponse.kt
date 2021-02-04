package com.adworkshop.retrofit


import com.google.gson.annotations.SerializedName


data  class ServiceMessageResponse(
    @SerializedName("message") val message : String,
    @SerializedName("data") val data : Data
)


data class Data (

    @SerializedName("SP_UID") val sP_UID : Int,
    @SerializedName("SP_NAME") val sP_NAME : String,
    @SerializedName("SP_CONTACT_NUMBER") val sP_CONTACT_NUMBER : Int,
    @SerializedName("EMAIL") val eMAIL : String,
    @SerializedName("recordsets") val recordsets : List<String>,

    @SerializedName("rowsAffected") val rowsAffected : List<Int>


)
