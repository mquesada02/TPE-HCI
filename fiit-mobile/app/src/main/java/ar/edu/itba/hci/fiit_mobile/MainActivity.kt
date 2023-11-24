package ar.edu.itba.hci.fiit_mobile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.hci.fiit_mobile.Components.BottomBar
import ar.edu.itba.hci.fiit_mobile.Components.TopAppBar
import ar.edu.itba.hci.fiit_mobile.ui.theme.FiitmobileTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkTheme by remember { mutableStateOf(false) }
            FiitmobileTheme (darkTheme) {
                val navController = rememberNavController()
                var showBars by rememberSaveable { mutableStateOf(true) }
                var showBottomBar by rememberSaveable { mutableStateOf(true) }
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                showBars = when (navBackStackEntry?.destination?.route) {
                    Screen.LoginScreen.route -> false
                    Screen.RegisterScreen.route -> false
                    Screen.ConfirmEmailScreen.route -> false
                    else -> true
                }

                //showBottomBar = !navBackStackEntry?.destination?.route?.contains("execute_routine")!!

                Scaffold(
                    topBar = { if (showBars) TopAppBar(navController, themeChange = { darkTheme = !darkTheme }, darkMode = darkTheme)  },
                    bottomBar = { if (showBars /*&& showBottomBar*/) BottomBar(navController)  },
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Box(
                        modifier = Modifier.padding(it)
                    ) {
                        FIITNavHost(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Testing")
@Composable
fun TestingPreview() {
    var darkTheme by remember { mutableStateOf(false) }
    FiitmobileTheme(darkTheme, dynamicColor = false) {
        val navController = rememberNavController()
        var showBars by rememberSaveable { mutableStateOf(true) }
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        showBars = when (navBackStackEntry?.destination?.route) {
            Screen.LoginScreen.route -> false
            Screen.RegisterScreen.route -> false
            Screen.ConfirmEmailScreen.route -> false
            else -> true
        }

        Scaffold(
            topBar = { if (showBars) TopAppBar(navController, themeChange = { darkTheme = !darkTheme }, darkMode = darkTheme)  },
             bottomBar = { if (showBars) BottomBar(navController)  },
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier.padding(it)
            ) {
                FIITNavHost(navController = navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(modifier: Modifier = Modifier, icon: ImageVector? = null, iconDesc: String? = null,
                       label: String, placeholder: String, text: TextFieldValue, onValueChange: (TextFieldValue) -> Unit,
                       keyboardOptions: KeyboardOptions = KeyboardOptions.Default, visualTransformation: VisualTransformation = VisualTransformation.None,
                       errorRules: Boolean = false)
{

    var showPassword by remember { mutableStateOf(false) }

    return OutlinedTextField(
        value = text,
        onValueChange = {onValueChange(it)},
        leadingIcon = {icon?.let { Icon(imageVector = icon, contentDescription = iconDesc) }},
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        visualTransformation = if (!showPassword) visualTransformation else VisualTransformation.None,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
        ),
        isError = errorRules,
        trailingIcon = {
            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(
                    imageVector = if(showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = "Toggle password visibility",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithIcons(modifier: Modifier = Modifier, icon: ImageVector? = null, iconDesc: String? = null,
                       label: String, placeholder: String, text: TextFieldValue, onValueChange: (TextFieldValue) -> Unit,
                       keyboardOptions: KeyboardOptions = KeyboardOptions.Default, visualTransformation: VisualTransformation = VisualTransformation.None,
                       errorRules: Boolean = false)
{

    return OutlinedTextField(
        value = text,
        onValueChange = {onValueChange(it)},
        leadingIcon = {icon?.let { Icon(imageVector = icon, contentDescription = iconDesc) }},
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
        ),
        isError = errorRules,
    )
}





