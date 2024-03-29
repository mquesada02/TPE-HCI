package ar.edu.itba.hci.fiit_mobile.data.network.model.routineCycles

import com.google.gson.annotations.SerializedName


data class NetworkRoutineCycleContent (

    @SerializedName("id"          ) var id          : Int,
    @SerializedName("name"        ) var name        : String,
    @SerializedName("detail"      ) var detail      : String,
    @SerializedName("type"        ) var type        : String,
    @SerializedName("order"       ) var order       : Int?    = null,
    @SerializedName("repetitions" ) var repetitions : Int,
    @SerializedName("metadata"    ) var metadata    : String? = null

)