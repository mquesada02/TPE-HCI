package ar.edu.itba.hci.fiit_mobile.data.network.model.cycleExercises

import com.google.gson.annotations.SerializedName


data class NetworkCycleExercise (

    @SerializedName("id"     ) var id     : Int,
    @SerializedName("name"   ) var name   : String,
    @SerializedName("detail" ) var detail : String,
    @SerializedName("type"   ) var type   : String,
    @SerializedName("date"   ) var date   : Long,
    @SerializedName("order"  ) var order  : Int

)