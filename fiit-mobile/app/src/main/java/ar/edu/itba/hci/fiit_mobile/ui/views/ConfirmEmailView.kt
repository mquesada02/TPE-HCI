package ar.edu.itba.hci.fiit_mobile.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.TextFieldWithIcons
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.LoginViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory

@Composable
fun ConfirmEmailScreen(onNavigateToLoginScreen: () -> Unit, viewModel: LoginViewModel = viewModel(factory = getViewModelFactory())) {
    val verificationCode = remember { mutableStateOf(TextFieldValue(""))}
    val validCode = remember { mutableStateOf(false)}
    val validEmail = remember { mutableStateOf(false)}
    val email = remember { mutableStateOf(TextFieldValue(""))}
    val onVerificationCodeChange = { value: TextFieldValue -> verificationCode.value = value; validCode.value = value.text.isNotEmpty() }
    val onEmailCodeChange = { value: TextFieldValue -> email.value = value; validEmail.value = email.value.text.isNotEmpty()}

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.verify_email),
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                lineHeight = MaterialTheme.typography.titleLarge.lineHeight,
                textAlign = TextAlign.Center
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.verify_email_message),
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
                textAlign = TextAlign.Center,
                letterSpacing = 0.5.sp
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextFieldWithIcons(
                label = "Email",
                placeholder = stringResource(R.string.enter_email),
                text = email.value,
                onValueChange = onEmailCodeChange,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextFieldWithIcons(
                label = stringResource(R.string.verification_code),
                placeholder = stringResource(R.string.introduce_verification_code),
                text = verificationCode.value,
                onValueChange = onVerificationCodeChange,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            ElevatedButton(
                onClick = {
                    viewModel.verifyEmail(email.value.text, verificationCode.value.text)
                    if (viewModel.uiState.error == null) {
                        onNavigateToLoginScreen()
                    } else {
                        /* TODO */
                    }
                },
                enabled = validCode.value && validEmail.value,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.verify),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}