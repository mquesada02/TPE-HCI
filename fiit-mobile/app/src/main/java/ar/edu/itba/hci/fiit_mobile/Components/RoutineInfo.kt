package ar.edu.itba.hci.fiit_mobile.Components

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import ar.edu.itba.hci.fiit_mobile.ui.states.HomeUiState
import ar.edu.itba.hci.fiit_mobile.ui.states.canGetAllFavourites
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.HomeViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory
import coil.compose.AsyncImage
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.JulianFields

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
    val score = data.score
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

    Column {
        Row(){
            RatingBar(maxRating = 5, currentRating = myRating,
                onRatingChanged = {myRating = it}, starsColor =MaterialTheme.colorScheme.outline,
                id = data.id, review = "")
            Text( text = score.toString())
        }
        Row(){
            Text(text =  stringResource(R.string.intensity),
                modifier = Modifier.padding(end = 5.dp))
            Text(text = intensityType)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ){
                AsyncImage(
                    model = data.user.avatarUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape).size(40.dp).height(40.dp)
                )


                Text(text = data.user.username, modifier = Modifier.padding(horizontal = 16.dp))
        }
        Text(dateToString(data.date))
        Row(){
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

    val gregorianDate = LocalDate.MIN.with(JulianFields.MODIFIED_JULIAN_DAY, date)
    return gregorianDate.format(DateTimeFormatter.ISO_DATE)
}

//@Preview
//@Composable
//fun test(){
//    RoutineInfo(data = NetworkRoutineContent(
//        id=0, name="test", detail="none", date=10,
//        score=4, isPublic = false, difficulty = "Hard",
//        user= NetworkUser(id=0, username = "Tester"), category = null,
//        metadata = NetworkRoutineMetadata(goals="none", img="what",
//            materials = "none", muscles = "eyes")
//    ))
//}