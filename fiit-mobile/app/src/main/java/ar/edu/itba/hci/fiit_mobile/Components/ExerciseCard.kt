package ar.edu.itba.hci.fiit_mobile.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.WindowInfo
import ar.edu.itba.hci.fiit_mobile.data.network.model.cycleExercises.NetworkCycleContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.routineCycles.NetworkRoutineCycleContent
import ar.edu.itba.hci.fiit_mobile.rememberWindowInfo
import ar.edu.itba.hci.fiit_mobile.ui.states.ExecuteRoutineUiState
import coil.compose.AsyncImage


@Composable
fun ExerciseDetailCard(cycles: ArrayList<NetworkRoutineCycleContent>, uiState: ExecuteRoutineUiState){
    val windowInfo = rememberWindowInfo()
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
        ){
        for((i, cycle) in cycles.withIndex()){
            if(i!=0){
                Divider(modifier = Modifier.padding(bottom = 14.dp), color = MaterialTheme.colorScheme.onSurface)
            }
            Column (
                modifier = Modifier.fillMaxWidth()
            ){
                Text(text = "${cycle.name}  -  Series: ${cycle.repetitions}",
                    fontSize =
                    if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact){22.sp}
                    else if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium) {25.sp} else {32.sp},
                    color = MaterialTheme.colorScheme.scrim,
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp))
                Exercise(exercises = uiState.exerciseMap[cycle.id] ?: arrayListOf(), uiState)
            }
        }
    }
}

@Composable
fun Exercise(exercises: ArrayList<NetworkCycleContent>, uiState: ExecuteRoutineUiState){
    val sec = stringResource(R.string.Seconds)
    val rep = stringResource(R.string.Repetitions)

    val windowInfo = rememberWindowInfo()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        for(exercise in exercises){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column(Modifier.weight(0.5f), horizontalAlignment = Alignment.CenterHorizontally){
                    if (uiState.imageMap[exercise.exercise.id] == null && uiState.isFetching) {
                        CircularProgressIndicator()
                    } else {
                        AsyncImage(
                            model = uiState.imageMap[exercise.exercise.id],
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = if(windowInfo.screenWidthInfo !is WindowInfo.WindowType.Expanded){Modifier.fillMaxWidth(0.9f)} else {Modifier.fillMaxWidth(0.65f)}
                        )
                    }

                }
                Column(Modifier.weight(0.5f), horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = exercise.exercise.name,
                            fontSize =
                        if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact){22.sp}
                        else if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium) {22.sp} else {28.sp},
                        color = MaterialTheme.colorScheme.scrim,
                        modifier = Modifier.padding(bottom = 15.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Replay,
                            contentDescription = "repetitions icon",
                            modifier = if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact){Modifier.size(26.dp)}
                            else if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium) {Modifier.size(26.dp)} else {Modifier.size(32.dp)},
                            tint = MaterialTheme.colorScheme.scrim
                        )
                        Text(
                            text = "  ${exercise.repetitions} $rep",
                            fontSize =
                            if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact){22.sp}
                            else if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium) {22.sp} else {30.sp},
                            color = MaterialTheme.colorScheme.scrim,
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Timer,
                            contentDescription = "timer icon",
                            modifier = if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact){Modifier.size(26.dp)}
                            else if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium) {Modifier.size(26.dp)} else {Modifier.size(32.dp)},
                            tint = MaterialTheme.colorScheme.scrim
                        )
                        Text(
                            text = "  ${exercise.duration} $rep",
                            fontSize =
                            if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact){22.sp}
                            else if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium) {22.sp} else {30.sp},
                            color = MaterialTheme.colorScheme.scrim,
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun exercisePreview(){

}
//
//
//@Composable
//fun ExerciseDetailCard(exercise: NetworkExerciseContent, cycleContent: NetworkCycleContent) {
//    val textColor = Color.Black
//    val windowInfo = rememberWindowInfo()
//    if(windowInfo.screenWidthInfo !is WindowInfo.WindowType.Expanded) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//                .background(MaterialTheme.colorScheme.secondary),
//            shape = MaterialTheme.shapes.medium,
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                // Image
//                Image(
//                    painter = rememberImagePainter(
//                        data = exercise.imgUrl,
//                        builder = {
//                            transformations(CircleCropTransformation())
//                        }
//                    ),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(60.dp)
//                        .clip(MaterialTheme.shapes.medium)
//                )
//
//                Spacer(modifier = Modifier.width(20.dp))
//
//                // Exercise Name
//                Text(
//                    text = exercise.name,
//                    color = textColor,
//                    fontSize = 16.sp,
//                    modifier = Modifier.padding(bottom = 4.dp)
//                )
//
//                // Repetitions and Seconds in the same Row
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 5.dp, end = 5.dp),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.End
//                ) {
//                    if(cycleContent.repetitions!=null){
//                        Row() { // Repetitions
//                            Icon(
//                                imageVector = Icons.Filled.Replay,
//                                contentDescription = "Icon",
//                                modifier = Modifier
//                                    .padding(top = 5.dp)
//                                    .size(10.dp)
//                            )
//                            Text(
//                                text = "${cycleContent.repetitions.toString()} ${stringResource(R.string.Repetitions)}",
//                                color = textColor,
//                                fontSize = 10.sp,
//                            )
//                        }
//                    }
//                    if(cycleContent.duration != null){
//                        Row() {// Seconds
//                            Icon(
//                                imageVector = Icons.Filled.Timer,
//                                contentDescription = "Icon",
//                                modifier = Modifier
//                                    .padding(top = 5.dp)
//                                    .size(10.dp)
//                            )
//                            Text(
//                                text = "${cycleContent.duration.toString()} ${stringResource(R.string.Seconds)}",
//                                color = textColor,
//                                fontSize = 10.sp,
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//    else{
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(20.dp)
//                .background(MaterialTheme.colorScheme.secondary),
//            shape = MaterialTheme.shapes.medium,
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                // Image
//                Image(
//                    painter = rememberImagePainter(
//                        data = exercise.imgUrl,
//                        builder = {
//                            transformations(CircleCropTransformation())
//                        }
//                    ),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(175.dp)
//                        .clip(MaterialTheme.shapes.medium)
//                )
//                Spacer(modifier = Modifier.width(50.dp))
//
//                // Exercise Name
//                Text(
//                    text = exercise.name,
//                    color = textColor,
//                    fontSize = 75.sp,
//                    modifier = Modifier.padding(bottom = 10.dp)
//                )
//                // Repetitions and Seconds in the same Row
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 5.dp, end = 20.dp),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.End
//                ) {
//                    if(cycleContent.repetitions != null){
//                        Row(){ // Repetitions
//                            Icon(imageVector = Icons.Filled.Replay,
//                                contentDescription = "Icon",
//                                modifier = Modifier
//                                    .padding(top = 15.dp)
//                                    .size(40.dp)
//                            )
//                            Text(
//                                text = "${cycleContent.repetitions.toString()} ${stringResource(R.string.Repetitions)}",
//                                color = textColor,
//                                fontSize = 50.sp,
//                            )
//                        }
//                    }
//
//                    if(cycleContent.duration != null){
//                        Row(){// Seconds
//                            Icon(imageVector = Icons.Filled.Timer,
//                                contentDescription = "Icon",
//                                modifier = Modifier
//                                    .padding(top = 15.dp)
//                                    .size(40.dp)
//                            )
//                            Text(
//                                text = "${cycleContent.duration.toString()} ${stringResource(R.string.Seconds)}",
//                                color = textColor,
//                                fontSize = 50.sp,
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun prevEx(){
//
//    ExerciseDetailCard(exercise = NetworkExerciseContent(id=0, name="nose12345678910", detail="alguno", type="vemos"),
//        cycleContent = NetworkCycleContent(exercise = NetworkCycleExercise(id=0, name="nose", detail="apa", type="susi"
//        , date = 0, order=0), duration = 3, repetitions = 2)
//    )
//}