package ar.edu.itba.hci.fiit_mobile.Components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.Screen
import ar.edu.itba.hci.fiit_mobile.WindowInfo
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineMetadata
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkUser
import ar.edu.itba.hci.fiit_mobile.rememberWindowInfo
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.HomeViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory
import coil.compose.AsyncImage

@Composable
private fun difficultyToIntensity(difficulty: String): String{
    if(difficulty=="rookie") {return stringResource(R.string.low_intensity)}
    if(difficulty=="intermediate") {return stringResource(R.string.mid_intensity)}
    return stringResource(R.string.high_intensity)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutineCard(onNavigateToScreen: (String) -> Unit, data : NetworkRoutineContent, viewModel: HomeViewModel = viewModel(factory = getViewModelFactory())){

    val windowInfo = rememberWindowInfo()

    if(windowInfo.screenWidthInfo !is WindowInfo.WindowType.Expanded) {
        Card(
            modifier = Modifier
                .aspectRatio(1f)
                .padding(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
            onClick = { viewModel.updateData(data); onNavigateToScreen("routine/" + data.id.toString()) }
            ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = data.metadata.img,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth() //para  forma de circulo se puede agregar un size fijo y .clip(CircleShape)
                        .fillMaxHeight(0.5f)
                        .padding(bottom = 5.dp)
                )
                Text(text = data.name, fontSize = 16.sp, textAlign = TextAlign.Center)
                Row(
                    modifier = Modifier
                        .padding(bottom = 3.dp)
                        .padding(start = 5.dp)
                        .padding(end = 5.dp)
                        .weight(0.15f),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column(horizontalAlignment = Alignment.Start, modifier = Modifier.weight(1f)) {
                        Text(
                            text = difficultyToIntensity(difficulty = data.difficulty),
                            fontSize = 13.sp
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxWidth(0.2f)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.Star,
                                contentDescription = "",
                                modifier = Modifier.size(15.dp)
                            )
                            Text(
                                text = "${data.score}",
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        }
    }
    else{ //cambiar los datos todo
        Card(
            modifier = Modifier
                .aspectRatio(1f)
                .padding(32.dp),
            onClick = { viewModel.updateData(data); onNavigateToScreen("routine/" + data.id.toString()) },
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
            ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = data.metadata.img,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth() //para  forma de circulo se puede agregar un size fijo y .clip(CircleShape)
                        .fillMaxHeight(0.5f)
                )
                Text(text = data.name, fontSize = 30.sp, textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.scrim)
                Row(
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .padding(start = 6.dp)
                        .padding(end = 6.dp)
                        .weight(0.15f),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column(horizontalAlignment = Alignment.Start, modifier = Modifier.weight(1f)) {
                        Text(
                            text = difficultyToIntensity(difficulty = data.difficulty),
                            fontSize = 21.sp,
                            color = MaterialTheme.colorScheme.scrim
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxWidth(0.2f)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.Star,
                                contentDescription = "",
                                modifier = Modifier.size(25.dp),
                                tint = MaterialTheme.colorScheme.scrim
                            )
                            Text(
                                text = "${data.score}",
                                fontSize = 21.sp,
                                color = MaterialTheme.colorScheme.scrim
                            )
                        }
                    }
                }
            }
        }
    }
}
