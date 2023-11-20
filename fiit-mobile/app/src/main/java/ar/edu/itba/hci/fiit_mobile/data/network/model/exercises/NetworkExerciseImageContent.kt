package ar.edu.itba.hci.fiit_mobile.data.network.model.exercises

import com.google.gson.annotations.SerializedName


data class NetworkExerciseImageContent (

    @SerializedName("id"     ) var id     : Int?    = null,
    @SerializedName("number" ) var number : Int?    = null,
    @SerializedName("url"    ) var url    : String

)