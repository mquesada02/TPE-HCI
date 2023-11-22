package ar.edu.itba.hci.fiit_mobile.Components

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.WindowInfo
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineMetadata
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkUser
import ar.edu.itba.hci.fiit_mobile.rememberWindowInfo
import ar.edu.itba.hci.fiit_mobile.ui.states.HomeUiState
import ar.edu.itba.hci.fiit_mobile.ui.states.canGetAllFavourites
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.HomeViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory
import coil.compose.AsyncImage
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun RoutineInfo(data : NetworkRoutineContent?, viewModel: HomeViewModel = viewModel(factory = getViewModelFactory())){

    if (data == null) {
        return
    }
    var fetchedFavourites by remember { mutableStateOf(false) }
    if (!fetchedFavourites) {
        viewModel.getFavourites()
        fetchedFavourites = true
    }
    val intensityType = data.difficulty
    var isFav by remember { mutableStateOf(isFav(viewModel.uiState, data.id)) }
    val icon = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder
    var myRating by remember { mutableIntStateOf(data.score) }
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    val context = LocalContext.current
    if(viewModel.uiState.favourites?.content?.isNotEmpty() == true) {
        isFav = isFav(viewModel.uiState, data.id)
    }
    val windowInfo = rememberWindowInfo()

    if (windowInfo.screenWidthInfo !is WindowInfo.WindowType.Expanded) {
        Column (horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()){
            Row() {
                RatingBar(
                    maxRating = 5,
                    currentRating = myRating,
                    onRatingChanged = { myRating = it },
                    starsColor = MaterialTheme.colorScheme.outline,
                    id = data.id,
                    size=15.dp
                )
            }
            Row() {
                Text(
                    text = stringResource(R.string.intensity),
                    modifier = Modifier.padding(end = 5.dp)
                )
                Text(text = intensityType)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = data.user.avatarUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(40.dp)
                        .height(40.dp)
                )
                Text(text = "${stringResource(R.string.Creator)}: ${data.user.username}", modifier = Modifier.padding(horizontal = 16.dp))
            }
            Text(dateToString(data.date))
            Row() {
                IconButton(onClick = {
                    context.startActivity(shareIntent)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Localized description",
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
                IconButton(onClick = {
                    if (!isFav) {
                        viewModel.addFavs(data.id)
                    } else {
                        viewModel.removeFavs(data.id)
                    }
                    viewModel.getFavourites()

                }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = if (isFav) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .size(30.dp)
                    )
                }
            }
        }
    }
    else{
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()) {
            Row() {
                RatingBar(
                    maxRating = 5,
                    currentRating = myRating,
                    onRatingChanged = { myRating = it },
                    starsColor = MaterialTheme.colorScheme.outline,
                    id = data.id,
                    size = 35.dp
                )
            }
            Row() {
                Text(
                    text = stringResource(R.string.intensity),
                    modifier = Modifier.padding(end = 5.dp),
                    fontSize = 35.sp
                )
                Text(text = intensityType, fontSize=35.sp)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = data.user.avatarUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(40.dp)
                        .height(40.dp)
                )
                Text(text = "${stringResource(R.string.Creator)}: ${data.user.username}", modifier = Modifier.padding(horizontal = 16.dp), fontSize=35.sp)
            }
            Text(dateToString(data.date), fontSize=35.sp)
            Row() {
                IconButton(onClick = {
                    context.startActivity(shareIntent)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Localized description",
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                IconButton(onClick = {
                    if (!isFav) {
                        viewModel.addFavs(data.id)
                    } else {
                        viewModel.removeFavs(data.id)
                    }
                    viewModel.getFavourites()

                }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = if (isFav) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
            }
        }
    }
}


fun isFav( ui : HomeUiState, id : Int): Boolean {
    if(ui.canGetAllFavourites && ui.favourites?.content?.isNotEmpty() == true){
        for(i in ui.favourites.content){
            if(id == i.id){
                return true
            }
        }
    }
    return false
}

fun dateToString(date: Long): String {
    if (date < 0) {
        throw IllegalArgumentException("Julian date cannot be negative")
    }
    val date = Date(date)
    return SimpleDateFormat("dd/MM/yyyy").format(date)
}

@Preview
@Composable
fun test(){
    val list = arrayListOf("Elemento 1", "Elemento 2", "Elemento 3")
    RoutineInfo(data = NetworkRoutineContent(
        id=0, name="test", detail="none", date=10,
        score=4, isPublic = false, difficulty = "Hard",
        user= NetworkUser(id=0, username = "Tester"), category = null,
        metadata = NetworkRoutineMetadata(goals=list, img="what",
            materials = list, muscles = list)
    ))
}