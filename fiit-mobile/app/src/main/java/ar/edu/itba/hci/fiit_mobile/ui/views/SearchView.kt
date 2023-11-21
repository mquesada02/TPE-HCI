package ar.edu.itba.hci.fiit_mobile.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.hci.fiit_mobile.Components.RoutineScroller
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.SearchViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = viewModel(factory = getViewModelFactory())
) {
    val searchText by viewModel.searchText.collectAsState()
    val routines by viewModel.routines.collectAsState()

    var fetchedRoutines by remember { mutableStateOf(false) }
    if(!fetchedRoutines){
        viewModel.getRoutines()
        fetchedRoutines = true
    }
    println(viewModel.routines)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        TextField(
            value = viewModel.uiState.error.toString(),
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {Text(stringResource(R.string.search))}
        )
        if(viewModel.uiState.isLoading){
            Box(modifier = Modifier.fillMaxSize()){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            RoutineScroller(name = "", routines = routines as ArrayList<NetworkRoutineContent>)
        }
    }

}