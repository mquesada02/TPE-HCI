package ar.edu.itba.hci.fiit_mobile.ui.views

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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

    val uiState = viewModel.uiState

    var fetchedRoutines by remember { mutableStateOf(false) }
    if(!fetchedRoutines){
        viewModel.getRoutines()
        fetchedRoutines = true
    }

    var isExpanded by remember{
        mutableStateOf(false)
    }

    var orderBy by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        TextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {Text(stringResource(R.string.search))}
        )

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {isExpanded = it}
        ) {
            TextField(
                value = orderBy,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = isExpanded, onDismissRequest = { isExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { stringResource(R.string.ddmenu_date) },
                    onClick = { orderBy = "date" }
                )
                DropdownMenuItem(
                    text = { stringResource(R.string.ddmenu_intensity) },
                    onClick = { orderBy = "intensity" }
                )
                DropdownMenuItem(
                    text = { stringResource(R.string.ddmenu_score) },
                    onClick = { orderBy = "score" }
                )
                DropdownMenuItem(
                    text = { stringResource(R.string.ddmenu_routinename) },
                    onClick = { orderBy = "routine" }
                )
                DropdownMenuItem(
                    text = { stringResource(R.string.ddmenu_username) },
                    onClick = { orderBy = "user" }
                )
            }
        }

        if(uiState.isLoading){
            if (routines.isNotEmpty()) {
                viewModel.updateLoad()
            }
            Box(modifier = Modifier.fillMaxSize()){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            RoutineScroller(name = null, routines = routines as ArrayList<NetworkRoutineContent>)
        }
    }

}