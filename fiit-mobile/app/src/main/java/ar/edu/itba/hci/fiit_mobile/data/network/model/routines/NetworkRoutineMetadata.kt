package ar.edu.itba.hci.fiit_mobile.data.network.model.routines

import com.google.gson.annotations.SerializedName

data class NetworkRoutineMetadata (
    @SerializedName("muscles" ) var muscles : String,
    @SerializedName("goals" ) var goals : String,
    @SerializedName("materials" ) var materials : String,
    @SerializedName("img" ) var img : String
)