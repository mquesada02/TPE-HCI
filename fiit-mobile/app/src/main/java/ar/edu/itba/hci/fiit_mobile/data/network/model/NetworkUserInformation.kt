package ar.edu.itba.hci.fiit_mobile.data.network.model

import com.google.gson.annotations.SerializedName
import java.util.Date


data class NetworkUserInformation (

    @SerializedName("username"  ) var username  : String? = null,
    @SerializedName("password"  ) var password  : String? = null,
    @SerializedName("firstName" ) var firstName : String? = null,
    @SerializedName("lastName"  ) var lastName  : String? = null,
    @SerializedName("gender"    ) var gender    : String? = null,
    @SerializedName("birthdate" ) var birthdate : Date?    = null,
    @SerializedName("email"     ) var email     : String? = null,
    @SerializedName("phone"     ) var phone     : String? = null,
    @SerializedName("avatarUrl" ) var avatarUrl : String? = null,
    @SerializedName("metadata"  ) var metadata  : Any? = null

)