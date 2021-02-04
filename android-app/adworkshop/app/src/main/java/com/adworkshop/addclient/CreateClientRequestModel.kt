package com.adworkshop.addclient


/*
* {
    "CLIENT_NAME" : "Eduardo Cardona",
    "CLIENT_TYPE" : "Individual",
    "COMPANY_CODE" : 3050981,
    "CONTACT_NUMBER" : 313313132,
    "EMAIL" : "eduardo@individual.com",
    "SP_UID" : 5
}
 */
data class CreateClientRequestModel(
    var CLIENT_NAME:String,
    var CLIENT_TYPE:String,
    var COMPANY_CODE:Long,
    var CONTACT_NUMBER:Long,
    var EMAIL:String,
    var SP_UID:Int
    )



