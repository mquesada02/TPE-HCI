package ar.edu.itba.hci.fiit_mobile.data.network.model.executions

import com.google.gson.annotations.SerializedName


data class NetworkExecution (

    @SerializedName("totalCount" ) var totalCount : Int,
    @SerializedName("orderBy"    ) var orderBy    : String,
    @SerializedName("direction"  ) var direction  : String,
    @SerializedName("content"    ) var content    : ArrayList<NetworkExecutionContent> = arrayListOf(),
    @SerializedName("size"       ) var size       : Int,
    @SerializedName("page"       ) var page       : Int,
    @SerializedName("isLastPage" ) var isLastPage : Boolean

)