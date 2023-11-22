package ar.edu.itba.hci.fiit_mobile.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent

//importante! cuando se lo llama no pasarle como parametro viewModel = RoutinesViewModel
//hacer viewModel = viewModel()
//sino se estaria destruyendo y regenerando la informacion cada vez que se cambia el estado de la pagina
//no usar el vonstructor de viewModel, llamar al factory de viewModel
@Composable
fun RoutineScroller(
    modifier: Modifier = Modifier, //para pasarle el padding desde scaffold (?
    name: String? = null,
    routines: ArrayList<NetworkRoutineContent>
){

    Column (
        modifier = Modifier.padding(8.dp)
    ) {
        if(name != null) {
            Text(text = name)
        }
        if(routines.isEmpty())
            Column(
                verticalArrangement = Arrangement.Center
            ){
                Text(text = stringResource(R.string.Empty), fontSize = 14.sp)
            }
        else{
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 160.dp),
                contentPadding = PaddingValues(
                    start = 10.dp,
                    top = 12.dp,
                    end = 10.dp,
                    bottom = 12.dp
                ),
                state = rememberLazyGridState(),
                content = {
                    items(routines.size){ index ->
                        RoutineCard(data = routines[index])
                    }
                }
            )
//            {
//                items(
//                    count = routines.size,
//                    key = { index -> routines[index].id.toString() }
//                ) { index ->
//                     RoutineCard(routines[index])
//                }
//            }
        }
    }
}