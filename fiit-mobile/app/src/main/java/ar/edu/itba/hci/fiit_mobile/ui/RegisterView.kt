package ar.edu.itba.hci.fiit_mobile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun RegisterScreen(onNavigateToConfirmEmailScreen : () -> Unit) {
    val email = remember { mutableStateOf(TextFieldValue("")) }
    val validEmail = remember { mutableStateOf(true)}
    val emailOnValueChange = { em: TextFieldValue -> email.value = em; validEmail.value = email.value.text.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email.value.text).matches() }

    val name = remember { mutableStateOf(TextFieldValue("")) }
    val validName = remember { mutableStateOf(true)}
    val nameOnValueChange = { na: TextFieldValue -> name.value = na; validName.value = name.value.text.isNotEmpty() && name.value.text.all { it.isLetter() } && name.value.text.length > 2 && name.value.text.length < 20}

    val surname = remember { mutableStateOf(TextFieldValue("")) }
    val validSurname = remember { mutableStateOf(true)}
    val surnameOnValueChange = { sur: TextFieldValue -> surname.value = sur; validSurname.value = surname.value.text.isNotEmpty() && surname.value.text.all { it.isLetter() } && surname.value.text.length > 2 && surname.value.text.length < 20}

    val username = remember { mutableStateOf(TextFieldValue("")) }
    val validUsername = remember { mutableStateOf(true)}
    val userOnValueChange = { user: TextFieldValue -> username.value = user; validUsername.value = username.value.text.isNotEmpty() && username.value.text.all { it.isLetterOrDigit() } && username.value.text.length > 2 && username.value.text.length < 20}

    val password = remember { mutableStateOf(TextFieldValue("")) }
    val validPassword = remember { mutableStateOf(true)}
    val passwordOnValueChange = { pass: TextFieldValue -> password.value = pass; validPassword.value = password.value.text.isNotEmpty() && password.value.text.length > 7 && password.value.text.length < 20 }

    val confirmPassword = remember { mutableStateOf(TextFieldValue("")) }
    val validConfirmPassword = remember { mutableStateOf(true)}
    val confirmPasswordOnValueChange = { conf: TextFieldValue -> confirmPassword.value = conf; validConfirmPassword.value = confirmPassword.value.text.isNotEmpty() && confirmPassword.value.text == password.value.text }

    val birthdate = remember { mutableStateOf(TextFieldValue("")) }
    val validBirthdate = remember { mutableStateOf(true)}
    val birthdateOnValueChange = { birth: TextFieldValue -> birthdate.value = birth; validBirthdate.value = birthdate.value.text.isNotEmpty() && birthdate.value.text.matches(Regex("\\d{1,2}-\\d{1,2}-\\d{2,4}")) }

    val weight = remember { mutableStateOf(TextFieldValue("")) }
    val validWeight = remember { mutableStateOf(true)}
    val weightOnValueChange = { wei: TextFieldValue -> weight.value = wei; validWeight.value = weight.value.text.isNotEmpty() && weight.value.text.all { it.isDigit() } && weight.value.text.toInt() > 0 && weight.value.text.toInt() < 500}

    val height = remember { mutableStateOf(TextFieldValue("")) }
    val validHeight = remember { mutableStateOf(true)}
    val heightOnValueChange = { hei: TextFieldValue -> height.value = hei; validHeight.value = height.value.text.isNotEmpty() && height.value.text.all { it.isDigit() } && height.value.text.toInt() > 0 && height.value.text.toInt() < 300}

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.verticalScroll(rememberScrollState()).background(MaterialTheme.colorScheme.background)
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
                    .padding(horizontal = 16.dp),
                text = email.value,
                onValueChange = emailOnValueChange,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                errorRules = !validEmail.value
            )

        }
        Row {
            if (!validEmail.value) {
                Text(
                    text = stringResource(R.string.invalid_email),
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = Color.Red,
                    modifier = Modifier.padding(horizontal = 18.dp).padding(top = 4.dp)
                )
            } else {
                Text(
                    text = "",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    modifier = Modifier.padding(horizontal = 18.dp).padding(top = 4.dp)
                )
            }
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
                    .padding(horizontal = 16.dp),
                text = name.value,
                onValueChange = nameOnValueChange,
                errorRules = !validName.value
            )
        }
        Row {
            if (!validName.value) {
                Text(
                    text = stringResource(R.string.invalid_name),
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = Color.Red,
                    modifier = Modifier.padding(horizontal = 18.dp).padding(top = 4.dp)
                )
            } else {
                Text(
                    text = "",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    modifier = Modifier.padding(horizontal = 18.dp).padding(top = 4.dp)
                )
            }
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
                    .padding(horizontal = 16.dp),
                text = surname.value,
                onValueChange = surnameOnValueChange,
                errorRules = !validSurname.value
            )
        }
        Row {
            if (!validSurname.value) {
                Text(
                    text = stringResource(R.string.invalid_surname),
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = Color.Red,
                    modifier = Modifier.padding(horizontal = 18.dp).padding(top = 4.dp)
                )
            } else {
                Text(
                    text = "",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    modifier = Modifier.padding(horizontal = 18.dp).padding(top = 4.dp)
                )
            }
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
                    .padding(horizontal = 16.dp),
                text = username.value,
                onValueChange = userOnValueChange,
                errorRules = !validUsername.value
            )
        }
        Row {
            if (!validUsername.value) {
                Text(
                    text = stringResource(R.string.invalid_username),
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = Color.Red,
                    modifier = Modifier.padding(horizontal = 18.dp).padding(top = 4.dp)
                )
            } else {
                Text(
                    text = "",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    modifier = Modifier.padding(horizontal = 18.dp).padding(top = 4.dp)
                )
            }
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
                    .padding(horizontal = 16.dp),
                text = password.value,
                onValueChange = passwordOnValueChange,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                errorRules = !validPassword.value
            )
        }
        Row {
            if (!validPassword.value) {
                Text(
                    text = stringResource(R.string.invalid_password),
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = Color.Red,
                    modifier = Modifier.padding(horizontal = 18.dp).padding(top = 4.dp)
                )
            } else {
                Text(
                    text = "",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    modifier = Modifier.padding(horizontal = 18.dp).padding(top = 4.dp)
                )
            }
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
                    .padding(horizontal = 16.dp),
                text = confirmPassword.value,
                onValueChange = confirmPasswordOnValueChange,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                errorRules = !validConfirmPassword.value
            )
        }
        Row {
            if (!validConfirmPassword.value) {
                Text(
                    text = stringResource(R.string.invalid_confirm_password),
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = Color.Red,
                    modifier = Modifier.padding(horizontal = 18.dp).padding(top = 4.dp)
                )
            } else {
                Text(
                    text = "",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    modifier = Modifier.padding(horizontal = 18.dp).padding(top = 4.dp)
                )
            }
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
                    .padding(horizontal = 16.dp),
                text = birthdate.value,
                onValueChange = birthdateOnValueChange,
                errorRules = !validBirthdate.value
            )
        }
        Row {
            if (!validBirthdate.value) {
                Text(
                    text = stringResource(R.string.invalid_birthdate),
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = Color.Red,
                    modifier = Modifier.padding(horizontal = 18.dp).padding(top = 4.dp)
                )
            } else {
                Text(
                    text = "",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    modifier = Modifier.padding(horizontal = 18.dp).padding(top = 4.dp)
                )
            }
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
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp),
                    isError = !validWeight.value,
                    singleLine = true
                )
                if (!validWeight.value) {
                    Text(
                        text = stringResource(R.string.invalid_weight),
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        color = Color.Red,
                        modifier = Modifier.padding(horizontal = 18.dp).padding(top = 4.dp)
                    )
                } else {
                    Text(
                        text = "",
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        modifier = Modifier.padding(horizontal = 18.dp).padding(top = 4.dp)
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.5f).weight(1f)
            ) {
                OutlinedTextField(
                    value = height.value,
                    onValueChange = heightOnValueChange,
                    label = { Text(text = stringResource(R.string.height)) },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp),
                    isError = !validHeight.value,
                    singleLine = true
                )
                if (!validHeight.value) {
                    Text(
                        text = stringResource(R.string.invalid_height),
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        color = Color.Red,
                        modifier = Modifier.padding(horizontal = 18.dp).padding(top = 4.dp)
                    )
                } else {
                    Text(
                        text = "",
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        modifier = Modifier.padding(horizontal = 18.dp).padding(top = 4.dp)
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            ElevatedButton(onClick = {
                                    /* TODO */
                                     onNavigateToConfirmEmailScreen()
                                     },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = Color.Black
                ),
                enabled =
                (email.value.text.isNotEmpty() && name.value.text.isNotEmpty() && surname.value.text.isNotEmpty() && username.value.text.isNotEmpty() && password.value.text.isNotEmpty() && confirmPassword.value.text.isNotEmpty() && birthdate.value.text.isNotEmpty() && weight.value.text.isNotEmpty() && height.value.text.isNotEmpty())
                &&
                (validEmail.value && validName.value && validSurname.value && validUsername.value && validPassword.value && validConfirmPassword.value && validBirthdate.value && validWeight.value && validHeight.value)
            ) {
                Text(text = stringResource(R.string.register), color = Color.Black, fontWeight = FontWeight.Bold, fontSize = MaterialTheme.typography.titleLarge.fontSize)
            }
        }
    }

}