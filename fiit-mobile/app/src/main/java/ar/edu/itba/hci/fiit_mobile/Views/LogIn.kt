package ar.edu.itba.hci.fiit_mobile.Views

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.itba.hci.fiit_mobile.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true, name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
fun LoginScreen() {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.login),
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,

                ) {
                TextField(
                    value = username.value,
                    onValueChange = {},
                    label = { Text(text = stringResource(R.string.user)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .shadow(4.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
            ) {
                TextField(
                    value = password.value,
                    onValueChange = {},
                    label = { Text(text = stringResource(R.string.password)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .shadow(4.dp)
                )
            }
        }
    }
}



