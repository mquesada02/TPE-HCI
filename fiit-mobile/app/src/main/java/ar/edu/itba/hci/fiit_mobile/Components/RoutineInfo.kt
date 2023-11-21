package ar.edu.itba.hci.fiit_mobile.Components

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.Gravity
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineMetadata
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkUser
import ar.edu.itba.hci.fiit_mobile.ui.states.HomeUiState
import ar.edu.itba.hci.fiit_mobile.ui.states.canAddFav
import ar.edu.itba.hci.fiit_mobile.ui.states.canDeleteFav
import ar.edu.itba.hci.fiit_mobile.ui.states.canGetAllFavourites
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.HomeViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory
import coil.compose.AsyncImage
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.JulianFields

@Composable
fun RoutineInfo(data : NetworkRoutineContent, viewModel: HomeViewModel = viewModel(factory = getViewModelFactory())){

    val intensityType = data.difficulty
    val score = data.score
    var isFav: Boolean = isFav(viewModel.uiState, data.id)
    val icon = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder
    var myRating by remember { mutableIntStateOf(data.score) }

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
        Row(){
            AsyncImage(
                model = data.user.avatarUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(40.dp).clip(CircleShape).size(10.dp)
            )
            Text(data.user.username)
        }
         // Text(dateToString(data.date)) //esta comentado porq mi date tdv es int todo
        Row(){
            IconButton(onClick = { /* tiene q copiar el link todo */ }) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = "Localized description",
                    modifier = Modifier
                        .size(20.dp)
                        .shadow(10.dp)
                )
            }
            IconButton(onClick = {
                if (isFav && viewModel.uiState.canAddFav) {
                    viewModel.addFavs(data.id)
                    isFav=isFav(viewModel.uiState, data.id)
                }
                if(viewModel.uiState.canDeleteFav){
                    viewModel.removeFavs(data.id)
                    isFav=isFav(viewModel.uiState, data.id)
                }
            }) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isFav) MaterialTheme.colorScheme.primary
                           else MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(30.dp)
                        .shadow(10.dp)
                )
            }
        }
    }
}


fun isFav( ui : HomeUiState, id : Int): Boolean {
    if(ui.canGetAllFavourites){
        for(i in ui.favourites?.content!!){
            if(id == i.id){
                return true
            }
        }
    }
    return false
}

@RequiresApi(Build.VERSION_CODES.O)
fun dateToString(date: Long): String {
    if (date < 0) {
        throw IllegalArgumentException("Julian date cannot be negative")
    }

    val gregorianDate = LocalDate.MIN.with(JulianFields.MODIFIED_JULIAN_DAY, date)
    return gregorianDate.format(DateTimeFormatter.ISO_DATE)
}

@Preview
@Composable
fun test(){
    RoutineInfo(data = NetworkRoutineContent(
        id=0, name="test", detail="none", date=10,
        score=4, isPublic = false, difficulty = "Hard",
        user= NetworkUser(id=0, username = "Tester"), category = null,
        metadata = NetworkRoutineMetadata(goals="none", img="what",
            materials = "none", muscles = "eyes")
    ))
}