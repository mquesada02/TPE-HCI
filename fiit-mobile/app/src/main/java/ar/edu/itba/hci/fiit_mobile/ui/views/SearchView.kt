package ar.edu.itba.hci.fiit_mobile.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import ar.edu.itba.hci.fiit_mobile.WindowInfo
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import ar.edu.itba.hci.fiit_mobile.rememberWindowInfo
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.SearchViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onNavigateToScreen: (String) -> Unit,
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

    val date = stringResource(R.string.ddmenu_date)
    val intensity = stringResource(R.string.ddmenu_intensity)
    val score = stringResource(R.string.ddmenu_score)
    val routinename = stringResource(R.string.ddmenu_routinename)
    val username = stringResource(R.string.ddmenu_username)

    val windowInfo = rememberWindowInfo()

    if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                placeholder = { Text(stringResource(R.string.search)) },
                colors = TextFieldDefaults.textFieldColors(containerColor= MaterialTheme.colorScheme.primary)
            )

            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { isExpanded = it },
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(0.8f),
            ) {
                TextField(
                    value = orderBy,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    placeholder = {
                        Text(stringResource(R.string.order_by))
                    },
                    colors = TextFieldDefaults.textFieldColors(containerColor= MaterialTheme.colorScheme.primary),
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = isExpanded, onDismissRequest = { isExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = date) },
                        onClick = { orderBy=date; viewModel.orderRoutines("date"); isExpanded = false}
                    )
                    DropdownMenuItem(
                        text = { Text(text = intensity) },
                        onClick = { orderBy = intensity; viewModel.orderRoutines("difficulty"); isExpanded = false }
                    )
                    DropdownMenuItem(
                        text = { Text(text = score) },
                        onClick = { orderBy = score; viewModel.orderRoutines("score"); isExpanded = false }
                    )
                    DropdownMenuItem(
                        text = { Text(text = routinename) },
                        onClick = { orderBy = routinename; viewModel.orderRoutines("routinename"); isExpanded = false }
                    )
                    DropdownMenuItem(
                        text = { Text(text = username) },
                        onClick = { orderBy = username; viewModel.orderRoutines("user"); isExpanded = false }
                    )
                }
            }

            if (uiState.isLoading) {
                if (routines.isNotEmpty()) {
                    viewModel.updateLoad()
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                RoutineScroller(onNavigateToScreen = onNavigateToScreen, name = null, routines = routines as ArrayList<NetworkRoutineContent>)

            }
        }
    } else { //-------otro tamaño----------
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                TextField(
                    value = searchText,
                    onValueChange = viewModel::onSearchTextChange,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(6.dp),
                    placeholder = { Text(stringResource(R.string.search)) },
                    colors = TextFieldDefaults.textFieldColors(containerColor= MaterialTheme.colorScheme.primary)
                )

                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = it },
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth(),
                ) {
                    TextField(
                        value = orderBy,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                        },
                        placeholder = {
                            Text(stringResource(R.string.order_by))
                        },
                        colors = TextFieldDefaults.textFieldColors(containerColor= MaterialTheme.colorScheme.primary),
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = isExpanded, onDismissRequest = { isExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = date) },
                            onClick = { orderBy=date; viewModel.orderRoutines("date"); isExpanded = false}
                        )
                        DropdownMenuItem(
                            text = { Text(text = intensity) },
                            onClick = { orderBy = intensity; viewModel.orderRoutines("difficulty"); isExpanded = false }
                        )
                        DropdownMenuItem(
                            text = { Text(text = score) },
                            onClick = { orderBy = score; viewModel.orderRoutines("score"); isExpanded = false }
                        )
                        DropdownMenuItem(
                            text = { Text(text = routinename) },
                            onClick = { orderBy = routinename; viewModel.orderRoutines("routinename"); isExpanded = false }
                        )
                        DropdownMenuItem(
                            text = { Text(text = username) },
                            onClick = { orderBy = username; viewModel.orderRoutines("user"); isExpanded = false }
                        )
                    }
                }
            }


            if (uiState.isLoading) {
                if (routines.isNotEmpty()) {
                    viewModel.updateLoad()
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                RoutineScroller(onNavigateToScreen = onNavigateToScreen, name = null, routines = routines as ArrayList<NetworkRoutineContent>)
            }
        }
    }

}