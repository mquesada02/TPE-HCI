package ar.edu.itba.hci.fiit_mobile.ui.views


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.LoginViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory
import coil.compose.AsyncImage


@Composable
fun ProfileScreen(onNavigateToLogin: () -> Unit, viewModel: LoginViewModel = viewModel(factory = getViewModelFactory())) {

    var fetchedCurrentUser by remember { mutableStateOf(false) }

    if (!fetchedCurrentUser) {
        viewModel.getCurrentUser()
        fetchedCurrentUser = true
    }
    val uiState = viewModel.uiState

    if (!uiState.isAuthenticated) { onNavigateToLogin() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp).verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.profile),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = uiState.currentUser?.avatarUrl ?: "https://imgur.com/vaFSz8D",
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )
        }

            PerfilItem(stringResource(R.string.name), uiState.currentUser?.firstName ?: "")

            PerfilItem(stringResource(R.string.surname), uiState.currentUser?.lastName ?: "")

            PerfilItem(stringResource(R.string.user), uiState.currentUser?.username?: "")

            PerfilItem("Email", uiState.currentUser?.email?: "")

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.weight) + ": ",
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = uiState.currentUser?.metadata?.weight.toString(),
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = stringResource(R.string.height) + ": ",
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = uiState.currentUser?.metadata?.height.toString(),
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize
                    )
                }


        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            val showDialog = remember { mutableStateOf(false) }
            val openDialog = { showDialog.value = true }
            val closeDialog = { showDialog.value = false }

            if (showDialog.value) {
                AlertDialog(
                    title = {Text(stringResource(R.string.confirm_logout))},
                    text = {Text(stringResource(R.string.confirmation_logout))},
                    onDismissRequest = { closeDialog() },
                    confirmButton = {
                        Button(
                            onClick = {
                            closeDialog()
                            viewModel.logout()
                        }
                        ) {
                            Text(stringResource(R.string.logout), color = MaterialTheme.colorScheme.error)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { closeDialog() }) {
                            Text(stringResource(R.string.cancel))
                        }
                    }
                )
            }
            Button(
                onClick = { openDialog() },
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = stringResource(R.string.logout), color = Color.Black)
            }
        }
    }
}






@Composable
fun PerfilItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(text = label, fontWeight = FontWeight.Bold, fontSize = MaterialTheme.typography.headlineSmall.fontSize)
        Text(text = value, fontSize = MaterialTheme.typography.headlineSmall.fontSize)
    }
}