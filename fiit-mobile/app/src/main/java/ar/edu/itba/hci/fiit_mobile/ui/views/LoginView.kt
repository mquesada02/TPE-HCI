package ar.edu.itba.hci.fiit_mobile.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.hci.fiit_mobile.PasswordTextField
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.Screen
import ar.edu.itba.hci.fiit_mobile.TextFieldWithIcons
import ar.edu.itba.hci.fiit_mobile.WindowInfo
import ar.edu.itba.hci.fiit_mobile.rememberWindowInfo
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.LoginViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory


@Composable
fun LoginScreen(onNavigateToScreen: (String) -> Unit, viewModel: LoginViewModel = viewModel(factory = getViewModelFactory())) {
    val username = remember { mutableStateOf(TextFieldValue("")) }
    val userOnValueChange = { user: TextFieldValue -> username.value = user }
    val password = remember { mutableStateOf(TextFieldValue("")) }
    val passwordOnValueChange = { pass: TextFieldValue -> password.value = pass }

    var showSnackbar by remember { mutableStateOf(false) }

    if (viewModel.uiState.isAuthenticated) {
        onNavigateToScreen(Screen.HomeScreen.route)
    }

    if (viewModel.uiState.error != null) {
        showSnackbar = true
    }


    val windowInfo = rememberWindowInfo()

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        if (windowInfo.screenWidthInfo !is WindowInfo.WindowType.Expanded) {
            if (windowInfo.screenHeightInfo !is WindowInfo.WindowType.Compact) {
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
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = MaterialTheme.colorScheme.onBackground
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
                        .padding(16.dp),
                    text = username.value,
                    onValueChange = userOnValueChange
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
            )
            {
                PasswordTextField(
                    icon = Icons.Rounded.Lock,
                    iconDesc = "lockIcon",
                    label = stringResource(R.string.password),
                    placeholder = stringResource(R.string.enter_password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
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
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    color = MaterialTheme.colorScheme.onBackground
                )
                ClickableText(
                    text = AnnotatedString(text = stringResource(R.string.no_account_register)),
                    onClick = { onNavigateToScreen(Screen.RegisterScreen.route) },
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        textDecoration = TextDecoration.Underline
                    ),
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                ElevatedButton(
                    onClick = { viewModel.login(username.value.text, password.value.text) },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.padding(bottom = 20.dp),
                    enabled = username.value.text.isNotEmpty() && password.value.text.isNotEmpty() && !viewModel.uiState.isFetching,
                ) {
                    Text(text = stringResource(R.string.login), color = Color.Black)
                }
            }
        }
        else{
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
                        .height(400.dp)
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
                    fontSize = 75.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Column( horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth() )
            {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth(0.8f),
                ) {
                    TextFieldWithIcons(
                        icon = Icons.Default.Person,
                        iconDesc = "personIcon",
                        label = stringResource(R.string.user),
                        placeholder = stringResource(R.string.enter_user),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .size(90.dp),
                        text = username.value,
                        onValueChange = userOnValueChange
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth(0.8f),
                ) {
                    TextFieldWithIcons(
                        icon = Icons.Rounded.Lock,
                        iconDesc = "lockIcon",
                        label = stringResource(R.string.password),
                        placeholder = stringResource(R.string.enter_password),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .size(90.dp),
                        text = password.value,
                        onValueChange = passwordOnValueChange,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation(),
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.no_account),
                    fontSize = 45.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                ClickableText(
                    text = AnnotatedString(text = stringResource(R.string.no_account_register)),
                    onClick = { onNavigateToScreen(Screen.RegisterScreen.route) },
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        textDecoration = TextDecoration.Underline,
                        fontSize = 45.sp
                    ),
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                ElevatedButton(
                    onClick = { viewModel.login(username.value.text, password.value.text) },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        disabledContainerColor = MaterialTheme.colorScheme.surface,
                        contentColor = Color.Black,
                    ),
                    modifier = Modifier.padding(bottom = 20.dp),
                    enabled = username.value.text.isNotEmpty() && password.value.text.isNotEmpty() && !viewModel.uiState.isFetching,
                ) {
                    Text(text = stringResource(R.string.login), color = Color.Black, fontSize = 50.sp)
                }
            }
        }
    }
    if(showSnackbar) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.error,
                shadowElevation = 4.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = stringResource(R.string.error_login),
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onError
                    )
                    IconButton(onClick = {viewModel.updateStateError(); showSnackbar = false}) {
                        Icon(
                           imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = MaterialTheme.colorScheme.onError
                        )
                    }
                }
            }
        }
    }

}


