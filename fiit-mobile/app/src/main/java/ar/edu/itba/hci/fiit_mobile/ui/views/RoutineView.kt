package ar.edu.itba.hci.fiit_mobile.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
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
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.hci.fiit_mobile.Components.RoutineInfo
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.HomeViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory
import coil.compose.AsyncImage

@Composable
fun RoutineScreen(onNavigateToScreen: (String) -> Unit, routineId: Int, viewModel : HomeViewModel = viewModel(factory = getViewModelFactory()))
{
    var fetchedRoutine by remember { mutableStateOf(false) }

    if (!fetchedRoutine) {
        viewModel.getRoutine(routineId)
        fetchedRoutine = true
    }

    var data = viewModel.uiState.currentRoutine

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
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
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ElevatedButton(onClick = {onNavigateToScreen("execute_routine/$routineId") }) {
                        Text(AnnotatedString(text = stringResource(R.string.start)))
                    }
                    RoutineInfo(data =data)
                }
            }
            Row() {
                Card(){
                    Text(text= stringResource(R.string.WarmUp))
                    // for(){
                    //   ExerciseDetailCard() todo
                    // }
                }
            }
            Row(
            ) {
                Card(){
                    Text(text= stringResource(R.string.Exercise))
                    // for(){
                    //   ExerciseDetailCard() todo
                    // }
                }
            }
            Row {
                Card(){
                    Text(text= stringResource(R.string.CoolDown))
                    // for(){
                    //   ExerciseDetailCard() todo
                    // }
                }
            }



    }

}