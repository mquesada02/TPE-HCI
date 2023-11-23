package ar.edu.itba.hci.fiit_mobile.data.network.model.reviews

import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import com.google.gson.annotations.SerializedName


data class NetworkUserReviewContent (

    @SerializedName("id"      ) var id      : Int?     = null,
    @SerializedName("date"    ) var date    : Int?     = null,
    @SerializedName("score"   ) var score   : Int?     = null,
    @SerializedName("review"  ) var review  : String?  = null,
    @SerializedName("routine" ) var routine : NetworkReviewRoutine? = null

)