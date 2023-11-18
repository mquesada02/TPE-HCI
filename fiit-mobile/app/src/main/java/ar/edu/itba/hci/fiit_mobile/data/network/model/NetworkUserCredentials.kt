package ar.edu.itba.hci.fiit_mobile.data.network.model

import com.google.gson.annotations.SerializedName


data class NetworkUserCredentials (

    @SerializedName("username" ) var username : String,
    @SerializedName("password" ) var password : String

)