package ar.edu.itba.hci.fiit_mobile.data.network.model

import com.google.gson.annotations.SerializedName


data class NetworkRoutineInformation (

    @SerializedName("name"       ) var name       : String,
    @SerializedName("detail"     ) var detail     : String,
    @SerializedName("isPublic"   ) var isPublic   : Boolean,
    @SerializedName("difficulty" ) var difficulty : String,
    @SerializedName("category"   ) var category   : NetworkRoutineCategoryID? = null,
    @SerializedName("metadata"   ) var imgUrl   : String?   = null

)