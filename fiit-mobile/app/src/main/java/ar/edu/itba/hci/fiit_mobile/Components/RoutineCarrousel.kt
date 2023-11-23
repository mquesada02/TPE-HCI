package ar.edu.itba.hci.fiit_mobile.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import ar.edu.itba.hci.fiit_mobile.WindowInfo
import ar.edu.itba.hci.fiit_mobile.data.network.model.executions.NetworkExecutionContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import ar.edu.itba.hci.fiit_mobile.rememberWindowInfo


//importante! cuando se lo llama no pasarle como parametro viewModel = RoutinesViewModel
//hacer viewModel = viewModel()
//sino se estaria destruyendo y regenerando la informacion cada vez que se cambia el estado de la pagina
//no usar el vonstructor de viewModel, llamar al factory de viewModel
@Composable
fun ExecutionCarrousel(onNavigateToScreen: (String) -> Unit, name : String, routines : ArrayList<NetworkExecutionContent>?){

    val windowInfo = rememberWindowInfo()

    Column (
        modifier = Modifier.padding(8.dp).fillMaxWidth(0.8f)
    ) {
        Text(text = name, fontSize = if (windowInfo.screenWidthInfo !is WindowInfo.WindowType.Expanded) 20.sp else 30.sp)
        if ((routines == null) || (routines.size == 0)){
                Column(
                    verticalArrangement = Arrangement.Center
                ){
                    Text(text = stringResource(R.string.empty_executions),
                        fontSize = 14.sp)
                }
        }
        else {
            LazyRow(
                modifier = Modifier.height(if (windowInfo.screenWidthInfo !is WindowInfo.WindowType.Expanded) 160.dp else 275.dp).fillMaxWidth(),
                state = rememberLazyListState(),
            ) {
                items(
                    count = routines.size,
                ) { index ->
                    RoutineCard(onNavigateToScreen, routines[index].routine!!)
                }
            }
        }
    }
}

@Composable
fun RoutineCarrousel(onNavigateToScreen: (String) -> Unit, name : String, routines : ArrayList<NetworkRoutineContent>?){

    val windowInfo = rememberWindowInfo()

    Column (
        modifier = Modifier.padding(8.dp).fillMaxWidth(0.8f)
    ) {
        Text(text = name, fontSize = if (windowInfo.screenWidthInfo !is WindowInfo.WindowType.Expanded) 20.sp else 30.sp)
        if ((routines == null) || (routines.size == 0)){
            Column(
                verticalArrangement = Arrangement.Center
            ){
                Text(text = stringResource(R.string.Empty),
                    fontSize = 14.sp)
            }
        }
        else {
            LazyRow(
                modifier = Modifier.height(if (windowInfo.screenWidthInfo !is WindowInfo.WindowType.Expanded) 160.dp else 275.dp).fillMaxWidth(),
                state = rememberLazyListState(),
            ) {
                items(
                    count = routines.size,
                ) { index ->
                    RoutineCard(onNavigateToScreen, routines[index])
                }
            }
        }
    }
}