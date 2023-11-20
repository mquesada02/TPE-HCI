package ar.edu.itba.hci.fiit_mobile.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.RoutineCard
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkRoutines
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.HomeViewModel

//importante! cuando se lo llama no pasarle como parametro viewModel = RoutinesViewModel
//hacer viewModel = viewModel()
//sino se estaria destruyendo y regenerando la informacion cada vez que se cambia el estado de la pagina
//no usar el vonstructor de viewModel, llamar al factory de viewModel
@Composable
fun RoutineScroller(
    modifier : Modifier = Modifier, //para pasarle el padding desde scaffold (?
    name : String,
    routines : NetworkRoutines
){

    Column (
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = name)
        if(routines.totalCount==0)
            Column(
                verticalArrangement = Arrangement.Center
            ){
                Text(text = stringResource(R.string.Empty), fontSize = 14.sp)
            }
        else{
            LazyColumn(
                state = rememberLazyListState(),
            ) {
                items(
                    count = routines.size,
                    key = { index -> routines.content[index].id.toString() }
                ) { index ->
                     RoutineCard(routines.content[index])
                }
            }
        }
    }
}