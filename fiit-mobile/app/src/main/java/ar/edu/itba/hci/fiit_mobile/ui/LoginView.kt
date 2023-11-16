package ar.edu.itba.hci.fiit_mobile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.TextFieldWithIcons

@Composable
fun LoginScreen() {
    val username = remember { mutableStateOf(TextFieldValue("")) }
    val userOnValueChange = { user: TextFieldValue -> username.value = user }
    val password = remember { mutableStateOf(TextFieldValue("")) }
    val passwordOnValueChange = { pass: TextFieldValue -> password.value = pass }

    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.fiit_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
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
            TextFieldWithIcons(
                icon = Icons.Default.Person,
                iconDesc = "personIcon",
                label = stringResource(R.string.user),
                placeholder = stringResource(R.string.enter_user),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(2.dp),
                text = username.value,
                onValueChange = userOnValueChange
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
        ) {
            TextFieldWithIcons(
                icon = Icons.Rounded.Lock,
                iconDesc = "lockIcon",
                label = stringResource(R.string.password),
                placeholder = stringResource(R.string.enter_password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(1.dp),
                text = password.value,
                onValueChange = passwordOnValueChange,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.no_account),
                fontSize = MaterialTheme.typography.labelMedium.fontSize
            )
            Text(
                text = stringResource(R.string.no_account_register),
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                textDecoration = TextDecoration.Underline
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            ElevatedButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = Color.Black
            )) {
                Text(text = stringResource(R.string.login), color = Color.Black)
            }
        }
    }
}