package ar.edu.itba.hci.fiit_mobile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun CountdownTimer(totalTimeInSeconds: Int) {
    var remainingTime by remember {mutableStateOf(totalTimeInSeconds)}

    LaunchedEffect(key1 = Unit) {
        while (remainingTime > 0) {
            delay(1000)
            remainingTime--
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Remaining time: ${remainingTime} seconds",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        CircularProgressIndicator(
            progress = remainingTime.toFloat() / totalTimeInSeconds.toFloat(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
    }
}
@Composable
fun ExecuteRoutineScreen() {
    val exercise = remember { mutableStateOf("Exercise") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
           horizontalArrangement = Arrangement.Center,
              modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = exercise.value,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CountdownTimer(totalTimeInSeconds = 10)
        }
    }
}