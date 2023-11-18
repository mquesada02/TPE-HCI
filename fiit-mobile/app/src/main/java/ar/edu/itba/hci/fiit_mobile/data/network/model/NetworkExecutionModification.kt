package ar.edu.itba.hci.fiit_mobile.data.network.model

import com.google.gson.annotations.SerializedName


data class NetworkExecutionModification (

    @SerializedName("duration"    ) var duration    : Int,
    @SerializedName("wasModified" ) var wasModified : Boolean

)