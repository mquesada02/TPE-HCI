package ar.edu.itba.hci.fiit_mobile
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.layout.HorizontalAlignmentLine
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import ar.edu.itba.hci.fiit_mobile.data.models.RoutinesViewModel
//import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkRoutines
//import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkUser
//import ar.edu.itba.hci.fiit_mobile.ui.states.RoutineState
//import coil.compose.AsyncImage
//
@Composable
fun RoutineCard(data: NetworkRoutineContent){}
//    Box(
//        modifier = Modifier
//            .width(100.dp)
//            .height(100.dp)
//            .background(MaterialTheme.colorScheme.background),
//    ){
//        Column (modifier = Modifier.fillMaxWidth()){
//            AsyncImage(
//                model = data.imgUrl,
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxWidth() //para  forma de circulo se puede agregar un size fijo y .clip(CircleShape)
//            )
//            Text(text = data.name, fontSize = 12.sp)
//            Row {
//                Text(text = data.user.username,
//                    textAlign = TextAlign.Start)
//                Text(text = "${data.score}",
//                    textAlign = TextAlign.End)
//            }
//        }
//    }
//}
//
////importante! cuando se lo llama no pasarle como parametro viewModel = RoutinesViewModel
////hacer viewModel = viewModel()
////sino se estaria destruyendo y regenerando la informacion cada vez que se cambia el estado de la pagina
////no usar el vonstructor de viewModel, llamar al factory de viewModel
//@Composable
//fun RoutineScroller(
//    modifier: Modifier = Modifier, //para pasarle el padding desde scaffold (?
//    name: String,
//    viewModel: RoutinesViewModel
//    ){
//    val routineState = viewModel.routineState
//
//    Column (
//        modifier = Modifier.padding(8.dp)
//    ) {
//        Text(text = name)
//        if(routineState.isLoading)
//            Column(
//                verticalArrangement = Arrangement.Center
//            ){
//                Text(text = stringResource(R.string.loading_routines),
//                fontSize = 14.sp)
//            }
//        else {
//            val routineList = routineState.routines?.content.orEmpty() //la lista de rutinas o una vacia
//            LazyRow(
//                state = rememberLazyListState(),
//            ) {
//                items(
//                    count = routineList.size,
//                    key = { index -> routineList[index].id.toString() }
//                ) { index ->
//                    RoutineCard(routineList[index])
//                }
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun RoutineCardPreview(){
//    RoutineCard(
//        NetworkRoutineContent(
//            id = 1,
//            name = "abdominales",
//            detail = "......",
//            date = 1234,
//            score = 4,
//            isPublic = false,
//            category = null,
//            difficulty = "intermediate",
//            user = NetworkUser(id = 1, username = "richas")
//        )
//    )
//}
