package ar.edu.itba.hci.fiit_mobile.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
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

                Spacer(modifier = Modifier.width(20.dp))

                // Repetitions and Seconds in the same Col
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    // Repetitions
                    Text(
                        text = cycleContent.repetitions?.toString() ?: "",
                        color = textColor,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(2.dp)
                            .height(20.dp)
                    )

                    // Seconds
                    Text(
                        text = cycleContent.duration?.toString() ?: "",
                        color = textColor,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .padding(2.dp)
                            .height(20.dp)
                    )
                }
            }
        }
    }
    else{ //chequear todo
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
                        .size(85.dp)
                        .clip(MaterialTheme.shapes.medium)
                )

                Spacer(modifier = Modifier.width(25.dp))

                // Exercise Name
                Text(
                    text = exercise.name,
                    color = textColor,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                Spacer(modifier = Modifier.width(25.dp))

                // Repetitions and Seconds in the same Row
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    // Repetitions
                    Text(
                        text = cycleContent.repetitions?.toString() ?: "",
                        color = textColor,
                        fontSize = 30.sp,
                        modifier = Modifier
                            .padding(2.dp)
                            .height(20.dp)
                    )

                    // Seconds
                    Text(
                        text = cycleContent.duration?.toString() ?: "",
                        color = textColor,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(5.dp)
                            .height(35.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun prevEx(){

    ExerciseDetailCard(exercise = NetworkExerciseContent(id=0, name="nose", detail="alguno", type="vemos"),
        cycleContent = NetworkCycleContent(exercise = NetworkCycleExercise(id=0, name="nose", detail="apa", type="susi"
        , date = 0, order=0 ))
    )
}