package ar.edu.itba.hci.fiit_mobile.data.network.model.executions

import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import com.google.gson.annotations.SerializedName


data class NetworkExecutionContent (

    @SerializedName("id"          ) var id          : Int,
    @SerializedName("date"        ) var date        : Long,
    @SerializedName("duration"    ) var duration    : Int,
    @SerializedName("wasModified" ) var wasModified : Boolean,
    @SerializedName("routine"     ) var routine     : NetworkRoutineContent? = null

)