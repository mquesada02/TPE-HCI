package ar.edu.itba.hci.fiit_mobile.ui.views


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
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
fun ExecuteRoutineScreen() {
    val exercise = remember { mutableStateOf("Exercise") }
    val cycleName = remember { mutableStateOf("Cycle") }
    val nextExercise = remember { mutableStateOf("Next Exercise") }
    val nextExerciseURL = remember { mutableStateOf("https://i.imgur.com/Q6Wp95h_d.webp?maxwidth=760&fidelity=grand")}

    var totalTime by remember { mutableLongStateOf(15L * 1000L) }
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
                text = exercise.value,
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
            Canvas(
                modifier = Modifier.size(100.dp), onDraw = {
                    drawCircle(
                        color = Color(0xFFFF927A),
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
                text = cycleName.value,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            ExerciseCard(name = nextExercise.value, img = nextExerciseURL.value)
        }
        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom
        ){
            Button(
                onClick = {
                    if(currentTime <= 0L) {
                        currentTime = totalTime
                        isTimerRunning = true
                    } else {
                        isTimerRunning = !isTimerRunning
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!isTimerRunning || currentTime <= 0L) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        Color.Red
                    }
                ),
            ) {
                Icon(
                    imageVector = if (!isTimerRunning || currentTime <= 0L) {
                        Icons.Default.PlayArrow
                    } else {
                        Icons.Default.Pause
                    },
                    contentDescription = "Play/Pause",
                    tint = Color.White
                )
            }

        }
    }


}