package ar.edu.itba.hci.fiit_mobile.data.network.model.reviews

import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineCategory
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkUser
import com.google.gson.annotations.SerializedName


data class NetworkReviewRoutine (

    @SerializedName("id"         ) var id         : Int?      = null,
    @SerializedName("name"       ) var name       : String?   = null,
    @SerializedName("detail"     ) var detail     : String?   = null,
    @SerializedName("date"       ) var date       : Int?      = null,
    @SerializedName("score"      ) var score      : Int?      = null,
    @SerializedName("isPublic"   ) var isPublic   : Boolean?  = null,
    @SerializedName("difficulty" ) var difficulty : String?   = null,
    @SerializedName("user"       ) var user       : NetworkUser?     = null,
    @SerializedName("category"   ) var category   : NetworkRoutineCategory? = null

)