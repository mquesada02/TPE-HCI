package ar.edu.itba.hci.fiit_mobile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.TextFieldWithIcons



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen() {
    val email = remember { mutableStateOf(TextFieldValue("")) }
    val emailOnValueChange = { em: TextFieldValue -> email.value = em }
    val name = remember { mutableStateOf(TextFieldValue("")) }
    val nameOnValueChange = { na: TextFieldValue -> name.value = na }
    val surname = remember { mutableStateOf(TextFieldValue("")) }
    val surnameOnValueChange = { sur: TextFieldValue -> surname.value = sur }
    val username = remember { mutableStateOf(TextFieldValue("")) }
    val userOnValueChange = { user: TextFieldValue -> username.value = user }
    val password = remember { mutableStateOf(TextFieldValue("")) }
    val passwordOnValueChange = { pass: TextFieldValue -> password.value = pass }
    val confirmPassword = remember { mutableStateOf(TextFieldValue("")) }
    val confirmPasswordOnValueChange = { conf: TextFieldValue -> confirmPassword.value = conf }
    val birthdate = remember { mutableStateOf(TextFieldValue("")) }
    val birthdateOnValueChange = { birth: TextFieldValue -> birthdate.value = birth }
    val weight = remember { mutableStateOf(TextFieldValue("")) }
    val weightOnValueChange = { wei: TextFieldValue -> weight.value = wei }
    val height = remember { mutableStateOf(TextFieldValue("")) }
    val heightOnValueChange = { hei: TextFieldValue -> height.value = hei }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(R.string.register),
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
        ) {
            TextFieldWithIcons(
                icon = Icons.Default.Email,
                iconDesc = "emailIcon",
                label = "Email",
                placeholder = stringResource(R.string.enter_email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(2.dp),
                text = email.value,
                onValueChange = emailOnValueChange,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
        ) {
            TextFieldWithIcons(
                icon = Icons.Default.Face,
                iconDesc = "faceIcon",
                label = stringResource(R.string.name),
                placeholder = stringResource(R.string.enter_name),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(2.dp),
                text = name.value,
                onValueChange = nameOnValueChange
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
        ) {
            TextFieldWithIcons(
                icon = Icons.Default.Face,
                iconDesc = "faceIcon",
                label = stringResource(R.string.surname),
                placeholder = stringResource(R.string.enter_surname),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(2.dp),
                text = surname.value,
                onValueChange = surnameOnValueChange
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
                text = email.value,
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
        ) {
            TextFieldWithIcons(
                icon = Icons.Rounded.Lock,
                iconDesc = "lockIcon",
                label = stringResource(R.string.confirm_password),
                placeholder = stringResource(R.string.enter_confirm_password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(1.dp),
                text = confirmPassword.value,
                onValueChange = confirmPasswordOnValueChange,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            TextFieldWithIcons(
                icon = Icons.Default.DateRange,
                iconDesc = "dateIcon",
                label = stringResource(R.string.birthdate),
                placeholder = stringResource(R.string.enter_birthdate),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(2.dp),
                text = birthdate.value,
                onValueChange = birthdateOnValueChange
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.5f).weight(1f)
            ) {
                OutlinedTextField(
                    value = weight.value,
                    onValueChange = weightOnValueChange,
                    label = { Text(text = stringResource(R.string.weight)) },
                    modifier = Modifier.width(100.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.5f).weight(1f)
            ) {
                OutlinedTextField(
                    value = height.value,
                    onValueChange = heightOnValueChange,
                    label = { Text(text = stringResource(R.string.height)) },
                    modifier = Modifier.width(100.dp)
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            ElevatedButton(onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = Color.Black
                )
            ) {
                Text(text = stringResource(R.string.register), color = Color.Black, fontWeight = FontWeight.Bold, fontSize = MaterialTheme.typography.titleLarge.fontSize)
            }
        }
    }

}