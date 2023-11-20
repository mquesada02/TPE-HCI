package ar.edu.itba.hci.fiit_mobile.data.network.model.cycleExercises

import com.google.gson.annotations.SerializedName


data class NetworkCycle (

    @SerializedName("totalCount" ) var totalCount : Int?               = null,
    @SerializedName("orderBy"    ) var orderBy    : String?            = null,
    @SerializedName("direction"  ) var direction  : String?            = null,
    @SerializedName("content"    ) var content    : ArrayList<NetworkCycleContent> = arrayListOf(),
    @SerializedName("size"       ) var size       : Int?               = null,
    @SerializedName("page"       ) var page       : Int?               = null,
    @SerializedName("isLastPage" ) var isLastPage : Boolean?           = null

)