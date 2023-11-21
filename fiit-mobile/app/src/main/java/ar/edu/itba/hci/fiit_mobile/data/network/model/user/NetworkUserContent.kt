package ar.edu.itba.hci.fiit_mobile.data.network.model.user

import com.google.gson.annotations.SerializedName

data class UserMetadata (
    @SerializedName("weight") var weight: String? = null,
    @SerializedName("height") var height: String? = null
)

data class NetworkUserContent (

    @SerializedName("id"           ) var id           : Int?     = null,
    @SerializedName("username"     ) var username     : String?  = null,
    @SerializedName("firstName"    ) var firstName    : String?  = null,
    @SerializedName("lastName"     ) var lastName     : String?  = null,
    @SerializedName("gender"       ) var gender       : String?  = null,
    @SerializedName("birthdate"    ) var birthdate    : Int?     = null,
    @SerializedName("email"        ) var email        : String?  = null,
    @SerializedName("phone"        ) var phone        : String?  = null,
    @SerializedName("avatarUrl"    ) var avatarUrl    : String?  = null,
    @SerializedName("metadata"     ) var metadata     : UserMetadata?  = null,
    @SerializedName("date"         ) var date         : Long?     = null,
    @SerializedName("lastActivity" ) var lastActivity : Long?     = null,
    @SerializedName("verified"     ) var verified     : Boolean? = null

)