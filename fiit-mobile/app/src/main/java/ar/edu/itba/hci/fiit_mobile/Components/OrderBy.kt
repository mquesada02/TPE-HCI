package ar.edu.itba.hci.fiit_mobile.Components

import androidx.compose.runtime.Composable
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutines


@Composable
fun orderByScore (data : NetworkRoutines) : ArrayList<NetworkRoutineContent>{
    data.content.sortBy { it.score }
    return data.content
}
@Composable
fun orderByDate (data : NetworkRoutines) : ArrayList<NetworkRoutineContent>{
    data.content.sortBy { it.date }
    return data.content
}
@Composable
fun orderByDifficulty (data : NetworkRoutines) : ArrayList<NetworkRoutineContent>{
    data.content.sortBy { it.difficulty }
    return data.content
}
//@Composable
//fun orderByCategory (data : NetworkRoutines) : ArrayList<NetworkRoutineContent>{
//    data.content.sortBy { it.metadata.muscles }
//    return data.content
//}