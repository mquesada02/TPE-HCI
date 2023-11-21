package ar.edu.itba.hci.fiit_mobile.ui.views


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.ExecuteRoutineViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory
import coil.compose.AsyncImage
import kotlinx.coroutines.delay

@Composable
fun ExerciseCard(name: String, img: String) {
    Row(
        modifier = Modifier.padding(horizontal = 30.dp)
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = img,
                    contentDescription = name
                )
            }
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = name,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    lineHeight = MaterialTheme.typography.headlineLarge.lineHeight,
                    textAlign = TextAlign.Center
                )

            }
        }
    }
}

@Composable
fun ExecuteRoutineScreen(routineId: Int, viewModel: ExecuteRoutineViewModel = viewModel(factory = getViewModelFactory())) {

    var fetchedCycles by remember { mutableStateOf(false) }
    var fetchedExercises by remember { mutableStateOf(false) }

    val uiState = viewModel.uiState
    if (!fetchedCycles) {
        // Call the function
        viewModel.updateCycles(routineId)
        fetchedCycles = true
    }
    if (uiState.cycles.isNotEmpty() && !fetchedExercises) {
        viewModel.getExercises(uiState.cycles[uiState.cycleIndex].id)
        fetchedExercises = true
    }

    //val nextExercise = remember { mutableStateOf(viewModel.getNextExercise(routineId)) }
    //val nextExerciseName = remember { mutableStateOf(nextExercise.vaExerciselue.exercise.name) }
    //viewModel.getNextExerciseImage(nextExercise.value.exercise.id) // update next and next image
    //val nextExerciseURL = remember { mutableStateOf( viewModel.uiState.nextExerciseImage ) }



    val hasPrevious = remember { mutableStateOf(false/*viewModel.hasPrevious()*/) }
    val hasNext = remember { mutableStateOf(false/*viewModel.hasNext()*/) }


    /* Timer */

    val clickedButton = remember { mutableStateOf(false) }

    var totalTime by remember { mutableLongStateOf(15L) }
    var currentTime by remember {mutableLongStateOf(totalTime)}
    var value by remember { mutableStateOf(1f) }
    var isTimerRunning by remember { mutableStateOf(false) }


    val textMeasurer = rememberTextMeasurer()
    val textToDraw = (currentTime / 1000L).toString()
    val style = TextStyle(
        fontSize = MaterialTheme.typography.titleLarge.fontSize,
        fontWeight = FontWeight.Bold,
        color = Color.White,
    )
    val textLayoutResult = remember(textToDraw) {
        textMeasurer.measure(textToDraw, style)
    }
    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if(currentTime > 0 && isTimerRunning) {
            delay(100L)
            currentTime -= 100L
            value = currentTime / totalTime.toFloat()
        } else {
            clickedButton.value = false
        }
    }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            Text(
                text = if (uiState.cycleExercises.isEmpty()) "Exercise" else
                    uiState.cycleExercises[uiState.exerciseIndex].exercise.name,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                lineHeight = MaterialTheme.typography.titleLarge.lineHeight,
                textAlign = TextAlign.Center
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            val color = MaterialTheme.colorScheme.secondary
            Canvas(
                modifier = Modifier.size(100.dp), onDraw = {
                    drawCircle(
                        color = color,
                        radius = 50.dp.toPx(),
                    )
                    drawText(
                        textMeasurer = textMeasurer,
                        text = textToDraw,
                        style = style,
                        topLeft = Offset(
                            x = center.x - textLayoutResult.size.width / 2,
                            y = center.y - textLayoutResult.size.height / 2,
                        )
                    )
                }
            )
        }
        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ){
            Text(
                text = if(uiState.cycles.isEmpty()) "Cycle" else uiState.cycles[uiState.cycleIndex].name,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            //ExerciseCard(name = nextExerciseName.value, img = nextExerciseURL.value)
        }
        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            verticalAlignment = Alignment.Bottom
        ){
            Button(
                onClick = {
                    /* TODO */
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!clickedButton.value)
                        MaterialTheme.colorScheme.background
                    else
                        MaterialTheme.colorScheme.primary,
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(),
                modifier = Modifier.size(75.dp),
                enabled = hasPrevious.value
            ) {
                Icon(
                    imageVector = Icons.Default.SkipPrevious,
                    contentDescription = "SkipPrevious",
                    tint = Color.Black,
                    modifier = Modifier.size(50.dp)
                )
            }
            Spacer(modifier = Modifier.width(35.dp))
            Button(
                onClick = {
                    if(currentTime <= 0L) {
                        currentTime = totalTime
                        isTimerRunning = true
                    } else {
                        isTimerRunning = !isTimerRunning
                    }
                    clickedButton.value = !clickedButton.value
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                    if (!clickedButton.value)
                        MaterialTheme.colorScheme.background
                    else
                        MaterialTheme.colorScheme.primary,
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(),
                modifier = Modifier.size(75.dp)
            ) {
                Icon(
                    imageVector = if (!isTimerRunning || currentTime <= 0L) {
                        Icons.Default.PlayArrow
                    } else {
                        Icons.Default.Pause
                    },
                    contentDescription = "Play/Pause",
                    tint = Color.Black,
                    modifier = Modifier.size(50.dp)
                )
            }
            Spacer(modifier = Modifier.width(35.dp))
            Button(
                onClick = {
                    /* TODO */
                          //current.value = nextExercise.value
                          //nextExercise.value = viewModel.getNextExercise(routineId)
                          //viewModel.getNextExerciseImage(nextExercise.value.exercise.id)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!clickedButton.value)
                        MaterialTheme.colorScheme.background
                    else
                        MaterialTheme.colorScheme.primary,
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(),
                modifier = Modifier.size(75.dp),
                enabled = hasNext.value
            ) {
                Icon(
                    imageVector = Icons.Default.SkipNext,
                    contentDescription = "SkipNext",
                    tint = Color.Black,
                    modifier = Modifier.size(50.dp)
                )
            }

        }
    }


}