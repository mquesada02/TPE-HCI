package ar.edu.itba.hci.fiit_mobile.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.data.models.RoutinesViewModel


//importante! cuando se lo llama no pasarle como parametro viewModel = RoutinesViewModel
//hacer viewModel = viewModel()
//sino se estaria destruyendo y regenerando la informacion cada vez que se cambia el estado de la pagina
//no usar el vonstructor de viewModel, llamar al factory de viewModel
@Composable
fun RoutineCarrousel(
    modifier: Modifier = Modifier, //para pasarle el padding desde scaffold (?
    name: String,
    viewModel: RoutinesViewModel
){
    val routineState = viewModel.routineState

    Column (
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = name)
        if(routineState.isLoading)
            Column(
                verticalArrangement = Arrangement.Center
            ){
                Text(text = stringResource(R.string.loading_routines),
                    fontSize = 14.sp)
            }
        else {
            val routineList = routineState.routines?.content.orEmpty() //la lista de rutinas o una vacia
            LazyRow(
                state = rememberLazyListState(),
            ) {
                items(
                    count = routineList.size,
                    key = { index -> routineList[index].id.toString() }
                ) { index ->
                    RoutineCard(routineList[index])
                }
            }
        }
    }
}