package ar.edu.itba.hci.fiit_mobile.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.ui.theme.FiitmobileTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import ar.edu.itba.hci.fiit_mobile.ui.theme.Pink80




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPerfilScreen() {
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

    val description = remember { mutableStateOf(TextFieldValue("")) }
    val descriptionOnValueChange = { desc: TextFieldValue -> description.value = desc}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink80)
            .padding(top = 20.dp)
    ) {
        // Place the photo at the top
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.fiit_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 20.dp, start = 25.dp, end = 25.dp)
        ) {
            OutlinedTextField(
                value = name.value,
                onValueChange =emailOnValueChange ,
                label = { Text(text = "Nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 50.dp, max = 100.dp)
                    .padding(end = 8.dp)
            )

            OutlinedTextField(
                value = surname.value,
                onValueChange = surnameOnValueChange,
                label = { Text(text = "Apellido") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 50.dp, max = 100.dp)
                    .padding(end = 8.dp)
            )

            OutlinedTextField(
                value = username,
                onValueChange =userOnValueChange,
                label =  "Nombre de usuario",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 50.dp, max = 100.dp)
                    .padding(end = 8.dp)
            )

            OutlinedTextField(
                value = description,
                onValueChange = descriptionOnValueChange,
                label =  "Descripción",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 50.dp, max = 100.dp)
                    .padding(end = 8.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = emailOnValueChange,
                label = "Correo electrónico",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 50.dp, max = 100.dp)
                    .padding(end = 8.dp)
            )

            OutlinedTextField(
                value = birthdate,
                onValueChange = birthdateOnValueChange,
                label = "Fecha de nacimiento",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 50.dp, max = 100.dp)
                    .padding(end = 8.dp)
            )

            OutlinedTextField(
                value = weight,
                onValueChange = weightOnValueChange,
                label = "Peso",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 50.dp, max = 100.dp)
                    .padding(end = 8.dp)
            )

            OutlinedTextField(
                value = height,
                onValueChange = heightOnValueChange,
                label = "Altura",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 50.dp, max = 100.dp)
                    .padding(end = 8.dp)
            )


            Button(
                onClick = { },//completar
                modifier = Modifier
                    .fillMaxWidth()
                    .width(50.dp)
                    .padding(top = 16.dp, bottom = 16.dp)
            ) {
                Text(text = "Guardar Cambios")
            }
        }
    }
}

fun OutlinedTextField(value: MutableState<TextFieldValue>, onValueChange: (TextFieldValue) -> Unit, label: String, modifier: Modifier) {

}


@Composable
fun EditablePerfilItem(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = label, fontWeight = FontWeight.Bold)
        BasicTextField(
            value = value,
            onValueChange = { onValueChange(it) },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditPerfilPreview() {
    FiitmobileTheme {
        EditPerfilScreen()
    }
}