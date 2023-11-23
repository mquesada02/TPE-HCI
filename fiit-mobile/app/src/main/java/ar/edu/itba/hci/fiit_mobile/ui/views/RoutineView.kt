package ar.edu.itba.hci.fiit_mobile.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.hci.fiit_mobile.Components.ExerciseDetailCard
import ar.edu.itba.hci.fiit_mobile.Components.RoutineInfo
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.WindowInfo
import ar.edu.itba.hci.fiit_mobile.data.network.model.routineCycles.NetworkRoutineCycleContent
import ar.edu.itba.hci.fiit_mobile.rememberWindowInfo
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.ExecuteRoutineViewModel
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.HomeViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory
import coil.compose.AsyncImage

@Composable
fun RoutineScreen(onNavigateToScreen: (String) -> Unit, routineId: Int, viewModel : HomeViewModel = viewModel(factory = getViewModelFactory()), xrViewModel: ExecuteRoutineViewModel = viewModel(factory = getViewModelFactory()))
{
    var fetchedRoutine by remember { mutableStateOf(false) }

    if (!fetchedRoutine) {
        viewModel.getRoutine(routineId)
        fetchedRoutine = true
    }

    val uiState = xrViewModel.uiState

    var fetchedCycles by remember { mutableStateOf(false) }
    var fetchedExercises by remember { mutableStateOf(false) }

    if (!fetchedCycles) {
        xrViewModel.updateCycles(routineId)
        fetchedCycles = true
    }
    if (uiState.cycles.isNotEmpty() && !fetchedExercises) {
        xrViewModel.swapCycles()
        xrViewModel.saveExercisesLoop()
        fetchedExercises = true
    }

    var data = viewModel.uiState.currentRoutine
    val windowInfo = rememberWindowInfo()
    var fontSize = if (windowInfo.screenWidthInfo !is WindowInfo.WindowType.Expanded){ 20.sp }  else{  45.sp }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
                if(data!=null){
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = data.name,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Row {
                        AsyncImage(
                            model = data.metadata.img,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(width = 100.dp, height = 100.dp),
                        )
                        //RoutineInfo(data = data!!)
                    }

                } else {
                    CircularProgressIndicator()
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                RoutineInfo(data =data)
            }
        }
        if(uiState.cycles.isEmpty()){
            CircularProgressIndicator()
        }else {
            Row() {
                val cycleArray = arrayListOf<NetworkRoutineCycleContent>(uiState.cycles[0])
                ExerciseDetailCard(cycles = cycleArray, uiState = uiState)
            }
            Row(
            ) {
                val cycleArray = arrayListOf<NetworkRoutineCycleContent>()
                for(i in 1 until uiState.cycles.size - 1){
                    cycleArray.add(uiState.cycles[i])
                }
                ExerciseDetailCard(cycles = cycleArray, uiState = uiState)
            }
            Row {
                val cycleArray = arrayListOf<NetworkRoutineCycleContent>(uiState.cycles[uiState.cycles.size - 1])
                ExerciseDetailCard(cycles = cycleArray, uiState = uiState)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            ElevatedButton(
                onClick = {
                    viewModel.addExecution(routineId)
                    onNavigateToScreen("execute_routine/$routineId")
                },
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .padding(16.dp)
            ) {
                Text(AnnotatedString(text = stringResource(R.string.start)))
            }
        }
    }
}