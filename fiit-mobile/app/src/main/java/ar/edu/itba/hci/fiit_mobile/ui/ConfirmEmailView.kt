package ar.edu.itba.hci.fiit_mobile.ui

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
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.TextFieldWithIcons

@Composable
fun ConfirmEmailScreen(onNavigateToLoginScreen: () -> Unit) {
    val verificationCode = remember { mutableStateOf(TextFieldValue(""))}
    val validCode = remember { mutableStateOf(false)}
    val onVerificationCodeChange = { value: TextFieldValue -> verificationCode.value = value; validCode.value = value.text.all { it.isDigit() }}

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
                    /* TODO */
                    onNavigateToLoginScreen()
                },
                enabled = validCode.value,
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