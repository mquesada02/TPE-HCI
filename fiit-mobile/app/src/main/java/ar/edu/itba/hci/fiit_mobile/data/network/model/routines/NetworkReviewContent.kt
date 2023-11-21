package ar.edu.itba.hci.fiit_mobile.data.network.model.routines

import com.google.gson.annotations.SerializedName


data class NetworkReviewContent (

    @SerializedName("id"     ) var id     : Int?    = null,
    @SerializedName("date"   ) var date   : Int?    = null,
    @SerializedName("score"  ) var score  : Int?    = null,
    @SerializedName("review" ) var review : String? = null

)