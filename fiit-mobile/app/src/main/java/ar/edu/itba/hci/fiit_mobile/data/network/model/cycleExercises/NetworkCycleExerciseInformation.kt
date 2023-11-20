package ar.edu.itba.hci.fiit_mobile.data.network.model.cycleExercises

import com.google.gson.annotations.SerializedName

data class NetworkCycleExerciseInformation (

    @SerializedName("order"       ) var order       : Int? = null,
    @SerializedName("duration"    ) var duration    : Int? = null,
    @SerializedName("repetitions" ) var repetitions : Int? = null

)