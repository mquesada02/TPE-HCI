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



@Composable
fun PerfilScreen() {
    val username = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val surname = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val birthdate= remember { mutableStateOf("") }
    val weight = remember { mutableStateOf("") }
    val height = remember { mutableStateOf("") }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink80)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 8.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Mi Perfil",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        }


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
                    .size(80.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 8.dp, bottom = 3.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text =name.value,
                fontSize = 30.sp,

                )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 2.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text =surname.value,
                fontSize = 30.sp,

                )
        }





        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 20.dp, start = 25.dp)
        ) {
            PerfilItem("Descripción", description.value, onValueChange = { newValue ->

                description.value = newValue
            })

            PerfilItem("Correo electrónico", email.value, onValueChange = { newValue ->

                email.value = newValue
            })

            PerfilItem("Fecha de Nacimiento", birthdate.value, onValueChange = { newValue ->

                birthdate.value = newValue
            })




            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 25.dp, end = 25.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Peso:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = weight.value, fontSize = 16.sp)
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Altura:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = height.value, fontSize = 16.sp)
            }

            Button(
                onClick = {},//completar
                modifier = Modifier
                    .fillMaxWidth()
                    .width(50.dp)
                    .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 20.dp)
            ) {
                Text(text = "Cerrar Sesión")
            }
        }
    }
}






@Composable
fun PerfilItem(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = label, fontWeight = FontWeight.Bold)
        BasicTextField(
            value = value,
            onValueChange = { newValue ->
                onValueChange(newValue)
            }

        )
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilPreview() {
    FiitmobileTheme {
        PerfilScreen()
    }
}