package ar.edu.itba.hci.fiit_mobile.data.network.model.routines

import com.google.gson.annotations.SerializedName


data class NetworkReview (

    @SerializedName("score"  ) var score  : Int?    = null,
    @SerializedName("review" ) var review : String? = null

)