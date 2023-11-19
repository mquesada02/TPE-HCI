package ar.edu.itba.hci.fiit_mobile.data.network.model.routines

import com.google.gson.annotations.SerializedName


data class NetworkRoutineCategory (

    @SerializedName("id"     ) var id     : Int,
    @SerializedName("name"   ) var name   : String,
    @SerializedName("detail" ) var detail : String

)