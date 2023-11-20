package ar.edu.itba.hci.fiit_mobile.data.network.model.user

import com.google.gson.annotations.SerializedName


data class NetworkEmailCode (

    @SerializedName("email" ) var email : String,
    @SerializedName("code"  ) var code  : String

)