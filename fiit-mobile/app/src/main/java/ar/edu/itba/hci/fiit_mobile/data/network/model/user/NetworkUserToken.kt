package ar.edu.itba.hci.fiit_mobile.data.network.model.user

import com.google.gson.annotations.SerializedName


data class NetworkUserToken (

    @SerializedName("token" ) var token : String

)