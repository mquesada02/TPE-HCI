package ar.edu.itba.hci.fiit_mobile.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.hci.fiit_mobile.WindowInfo
import ar.edu.itba.hci.fiit_mobile.rememberWindowInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuCard(name: String, iconType: ImageVector, onClick: () -> Unit) {

    val windowInfo = rememberWindowInfo()
    if(windowInfo.screenWidthInfo !is WindowInfo.WindowType.Expanded) {
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
            modifier = Modifier.size(width = 150.dp, height = 150.dp),
            //shape = RoundedCornerShape(20.dp),
            onClick = { onClick() }
            ){Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ){
                Icon(
                    imageVector = iconType,
                    contentDescription = "Icon",
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier.size(70.dp)
                )
                Text(
                    text = name,
                    color = MaterialTheme.colorScheme.background,
                    fontSize = 21.sp
                )
            }
        }
    }
    else{
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
            modifier = Modifier.size(width = 310.dp, height = 310.dp),
            shape = RoundedCornerShape(25.dp),
            onClick = { onClick() }
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ){
                Icon(
                    imageVector = iconType,
                    contentDescription = "Icon",
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier.size(120.dp)
                )
                Text(
                    text = name,
                    color = MaterialTheme.colorScheme.background,
                    fontSize = 40.sp
                )
            }
        }
    }
}
