package ar.edu.itba.hci.fiit_mobile.Components

import android.content.Intent
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.RatingBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
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

@Composable
fun RoutineInfo(data : NetworkRoutineContent, viewModel: HomeViewModel = viewModel(factory = getViewModelFactory())){

    val intensityType = data.difficulty
    val score = data.score
    var isFav: Boolean = isFav(viewModel.uiState, data.id)
    val icon = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder

    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    val context = LocalContext.current

    Column {
        Row(){
           /* val ratingBar: RatingBar = RatingBar(this).apply {
                setIsIndicator(false)
                numStars = 5
                stepSize = 1.0f
                rating = data.score.toFloat()
                max = 5
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { gravity = Gravity.CENTER }
            }*/
            // Puntuaje en estrellitas q actualiza al momento la variable score todo
            Text( text = score.toString())
        }
        Row(){
            Text(text =  stringResource(R.string.intensity),
                modifier = Modifier.padding(end = 5.dp))
            Text(text = intensityType)
        }
        Row(){
            IconButton(onClick = {
                context.startActivity(shareIntent)
            }) {
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