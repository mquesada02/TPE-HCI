package ar.edu.itba.hci.fiit_mobile.data.network.model.exercises

import com.google.gson.annotations.SerializedName


data class NetworkExerciseInformation (

    @SerializedName("name"     ) var name     : String,
    @SerializedName("detail"   ) var detail   : String,
    @SerializedName("type"     ) var type     : String,
    @SerializedName("metadata" ) var metadata : String? = null

)