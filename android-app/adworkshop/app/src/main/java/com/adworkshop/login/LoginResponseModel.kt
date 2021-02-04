package com.adworkshop.login

import com.google.gson.annotations.SerializedName


data class LoginResponseDataModel(
    @SerializedName("aaData") val aaData : AaData,
    @SerializedName("message") val message : String,
    @SerializedName("status") val status : Boolean,
    @SerializedName("statusCode") val statusCode : Int
)

data class AaData (

    @SerializedName("token") val token : String,
    @SerializedName("uid") val uid : String,
    @SerializedName("user_name") val user_name : String,
    @SerializedName("user_uuid") val user_uuid : String,
    @SerializedName("user_status") val user_status : String,
    @SerializedName("mobile_number") var mobile_number : String,
    @SerializedName("emailaddress") var emailaddress : String,
    @SerializedName("user_type") val user_type : String,
    @SerializedName("company_logo") val company_logo : String,
    @SerializedName("stripe_customer_id") val stripe_customer_id : String,
    @SerializedName("latitude") val latitude : String,
    @SerializedName("longitude") val longitude : String,
    @SerializedName("whatsapp_number") val whatsapp_number : String,
    @SerializedName("company_address") val company_address : String,
    @SerializedName("company_registration_number") val company_registration_number : String,
    @SerializedName("company_name") val company_name : String,
    @SerializedName("first_name") var first_name : String,
    @SerializedName("last_name") var last_name : String,
    @SerializedName("customer_image") var customer_image : String,
    @SerializedName("emergency_contact_number") var emergency_contact_number : String
)