package ar.edu.itba.hci.fiit_mobile.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.hci.fiit_mobile.WindowInfo
import ar.edu.itba.hci.fiit_mobile.data.network.model.exercises.NetworkExerciseContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.cycleExercises.NetworkCycleContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.cycleExercises.NetworkCycleExercise
import ar.edu.itba.hci.fiit_mobile.rememberWindowInfo
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import ar.edu.itba.hci.fiit_mobile.R


@Composable
fun ExerciseDetailCard(exercise: NetworkExerciseContent, cycleContent: NetworkCycleContent) {
    val textColor = Color.Black
    val windowInfo = rememberWindowInfo()
    if(windowInfo.screenWidthInfo !is WindowInfo.WindowType.Expanded) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.secondary),
            shape = MaterialTheme.shapes.medium,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Image
                Image(
                    painter = rememberImagePainter(
                        data = exercise.imgUrl,
                        builder = {
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(MaterialTheme.shapes.medium)
                )

                Spacer(modifier = Modifier.width(20.dp))

                // Exercise Name
                Text(
                    text = exercise.name,
                    color = textColor,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                // Repetitions and Seconds in the same Row
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp, end = 5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    if(cycleContent.repetitions!=null){
                        Row() { // Repetitions
                            Icon(
                                imageVector = Icons.Filled.Replay,
                                contentDescription = "Icon",
                                modifier = Modifier
                                    .padding(top = 5.dp)
                                    .size(10.dp)
                            )
                            Text(
                                text = "${cycleContent.repetitions.toString()} ${stringResource(R.string.Repetitions)}",
                                color = textColor,
                                fontSize = 10.sp,
                            )
                        }
                    }
                    if(cycleContent.duration != null){
                        Row() {// Seconds
                            Icon(
                                imageVector = Icons.Filled.Timer,
                                contentDescription = "Icon",
                                modifier = Modifier
                                    .padding(top = 5.dp)
                                    .size(10.dp)
                            )
                            Text(
                                text = "${cycleContent.duration.toString()} ${stringResource(R.string.Seconds)}",
                                color = textColor,
                                fontSize = 10.sp,
                            )
                        }
                    }
                }
            }
        }
    }
    else{
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .background(MaterialTheme.colorScheme.secondary),
            shape = MaterialTheme.shapes.medium,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Image
                Image(
                    painter = rememberImagePainter(
                        data = exercise.imgUrl,
                        builder = {
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(175.dp)
                        .clip(MaterialTheme.shapes.medium)
                )
                Spacer(modifier = Modifier.width(50.dp))

                // Exercise Name
                Text(
                    text = exercise.name,
                    color = textColor,
                    fontSize = 75.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                // Repetitions and Seconds in the same Row
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp, end = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    if(cycleContent.repetitions != null){
                        Row(){ // Repetitions
                            Icon(imageVector = Icons.Filled.Replay,
                                contentDescription = "Icon",
                                modifier = Modifier
                                    .padding(top = 15.dp)
                                    .size(40.dp)
                            )
                            Text(
                                text = "${cycleContent.repetitions.toString()} ${stringResource(R.string.Repetitions)}",
                                color = textColor,
                                fontSize = 50.sp,
                            )
                        }
                    }

                    if(cycleContent.duration != null){
                        Row(){// Seconds
                            Icon(imageVector = Icons.Filled.Timer,
                                contentDescription = "Icon",
                                modifier = Modifier
                                    .padding(top = 15.dp)
                                    .size(40.dp)
                            )
                            Text(
                                text = "${cycleContent.duration.toString()} ${stringResource(R.string.Seconds)}",
                                color = textColor,
                                fontSize = 50.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun prevEx(){

    ExerciseDetailCard(exercise = NetworkExerciseContent(id=0, name="nose12345678910", detail="alguno", type="vemos"),
        cycleContent = NetworkCycleContent(exercise = NetworkCycleExercise(id=0, name="nose", detail="apa", type="susi"
        , date = 0, order=0), duration = 3, repetitions = 2)
    )
}