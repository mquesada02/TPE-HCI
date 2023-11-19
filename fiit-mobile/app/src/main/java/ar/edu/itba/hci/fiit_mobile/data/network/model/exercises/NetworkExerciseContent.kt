package ar.edu.itba.hci.fiit_mobile.data.network.model.exercises

import com.google.gson.annotations.SerializedName


data class NetworkExerciseContent (

    @SerializedName("id"       ) var id       : Int,
    @SerializedName("name"     ) var name     : String,
    @SerializedName("detail"   ) var detail   : String,
    @SerializedName("type"     ) var type     : String,
    @SerializedName("date"     ) var date     : Int?    = null,
    @SerializedName("metadata" ) var imgUrl : String? = null

)