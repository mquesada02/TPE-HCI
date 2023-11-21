package ar.edu.itba.hci.fiit_mobile.data.network.model.routines

import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkUser
import com.google.gson.annotations.SerializedName


data class NetworkRoutineContent (

    @SerializedName("id"         ) var id         : Int,
    @SerializedName("name"       ) var name       : String,
    @SerializedName("detail"     ) var detail     : String,
    @SerializedName("date"       ) var date       : Int,
    @SerializedName("score"      ) var score      : Int,
    @SerializedName("isPublic"   ) var isPublic   : Boolean,
    @SerializedName("difficulty" ) var difficulty : String,
    @SerializedName("user"       ) var user       : NetworkUser,
    @SerializedName("category"   ) var category   : NetworkRoutineCategory? = null,
    @SerializedName("metadata"   ) var metadata   : NetworkRoutineMetadata

){
    fun matchesStringQuery(query: String): Boolean{
        val matchingCombinations = listOf(
            name,
            metadata.muscles,
            metadata.goals,
            user.username
        )

        return matchingCombinations.any{
            it.contains(query, ignoreCase = true)
        }
    }
}