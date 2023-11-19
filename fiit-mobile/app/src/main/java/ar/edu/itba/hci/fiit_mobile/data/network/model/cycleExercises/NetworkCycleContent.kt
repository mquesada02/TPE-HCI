package ar.edu.itba.hci.fiit_mobile.data.network.model.cycleExercises

import com.google.gson.annotations.SerializedName


data class NetworkCycleContent (

    @SerializedName("exercise"    ) var exercise    : NetworkCycleExercise,
    @SerializedName("order"       ) var order       : Int?      = null,
    @SerializedName("duration"    ) var duration    : Int?      = null,
    @SerializedName("repetitions" ) var repetitions : Int?      = null,
    @SerializedName("metadata"    ) var metadata    : String?   = null

)

