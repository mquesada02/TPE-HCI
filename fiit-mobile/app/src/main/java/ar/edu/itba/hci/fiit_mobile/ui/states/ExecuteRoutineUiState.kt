package ar.edu.itba.hci.fiit_mobile.ui.states

import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkError
import ar.edu.itba.hci.fiit_mobile.data.network.model.cycleExercises.NetworkCycleContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.routineCycles.NetworkRoutineCycleContent

data class ExecuteRoutineUiState(
    val cycles: ArrayList<NetworkRoutineCycleContent> = arrayListOf(),
    val cycleIndex: Int = 0,
    val totalCycles: Int = 0,

    val cycleExercises: ArrayList<NetworkCycleContent> = arrayListOf(),
    val exerciseIndex: Int = 0,
    val totalExercises: Int = 0,

    val nextExerciseImage: String = "",

    val isFetching: Boolean = false,
    val error: NetworkError? = null
) {

}
