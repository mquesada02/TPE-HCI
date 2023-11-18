package ar.edu.itba.hci.fiit_mobile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.hci.fiit_mobile.ui.ExecuteRoutineScreen
import ar.edu.itba.hci.fiit_mobile.ui.theme.FiitmobileTheme
import ar.edu.itba.hci.fiit_mobile.ui.LoginScreen
import ar.edu.itba.hci.fiit_mobile.ui.RegisterScreen

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FiitmobileTheme {
                val navController = rememberNavController()
                Scaffold(
                    /* topBar = ,*/
                    /* bottomBar = ,*/
                    modifier = Modifier.fillMaxSize()
                ) {
                    FIITNavHost(navController = navController)
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



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Testing")
@Composable
fun TestingPreview() {

    FiitmobileTheme(dynamicColor = false) {
        val navController = rememberNavController()
        Scaffold(
            /* topBar = ,*/
            /* bottomBar = ,*/
            modifier = Modifier.fillMaxSize()
        ) {
            FIITNavHost(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithIcons(modifier: Modifier = Modifier, icon: ImageVector? = null, iconDesc: String? = null, label: String, placeholder: String, text: TextFieldValue, onValueChange: (TextFieldValue) -> Unit, keyboardOptions: KeyboardOptions = KeyboardOptions.Default, visualTransformation: VisualTransformation = VisualTransformation.None, errorRules: Boolean = false) {
    return OutlinedTextField(
        value = text,
        leadingIcon = {icon?.let { Icon(imageVector = icon, contentDescription = iconDesc) }},
        onValueChange = {onValueChange(it)},
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
        isError = errorRules
    )
}

//@Preview(showBackground = true, name = "Testing", uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun testingPreview() {
//    FiitmobileTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            LoginScreen()
//        }
//    }
//}





