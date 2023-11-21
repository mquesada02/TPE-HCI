package ar.edu.itba.hci.fiit_mobile.data.network.model.routines

import com.google.gson.annotations.SerializedName


data class NetworkReviewContent (

    @SerializedName("id"     ) var id     : Int,
    @SerializedName("date"   ) var date   : Long,
    @SerializedName("score"  ) var score  : Int,
    @SerializedName("review" ) var review : String? = null

)