package ar.edu.itba.hci.fiit_mobile.data.network.model.user

import com.google.gson.annotations.SerializedName


data class NetworkUser (

    @SerializedName("id"           ) var id           : Int,
    @SerializedName("username"     ) var username     : String,
    @SerializedName("gender"       ) var gender       : String? = null,
    @SerializedName("avatarUrl"    ) var avatarUrl    : String? = null,
    @SerializedName("date"         ) var date         : Int? = null,
    @SerializedName("lastActivity" ) var lastActivity : Int?    = null

)