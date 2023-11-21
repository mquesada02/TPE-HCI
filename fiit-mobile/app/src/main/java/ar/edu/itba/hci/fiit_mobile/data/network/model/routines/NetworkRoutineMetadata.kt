package ar.edu.itba.hci.fiit_mobile.data.network.model.routines

import com.google.gson.annotations.SerializedName

data class NetworkRoutineMetadata (
    @SerializedName("muscles" ) var muscles : ArrayList<String> = arrayListOf(),
    @SerializedName("goals" ) var goals : ArrayList<String> = arrayListOf(),
    @SerializedName("materials" ) var materials : ArrayList<String> = arrayListOf(),
    @SerializedName("img" ) var img : String
)