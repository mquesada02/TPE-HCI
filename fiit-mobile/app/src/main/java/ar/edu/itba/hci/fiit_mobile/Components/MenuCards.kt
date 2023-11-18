package ar.edu.itba.hci.fiit_mobile.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.hci.fiit_mobile.R

@Composable
//@Preview(showBackground = true, name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
fun MenuCard(name: String, iconType : ImageVector) {

    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
        modifier = Modifier.size(width = 150.dp, height = 150.dp),
        shape = RoundedCornerShape(20.dp)
    ){
        Box(
            modifier = Modifier.fillMaxSize() .background(MaterialTheme.colorScheme.secondary),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ) {
                Icon(
                    imageVector = iconType,
                    contentDescription = "Icon",
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .padding(bottom = 25.dp)
                        .size(40.dp)
                )
                Text(
                    text = name,
                    color = Color.Black // MaterialTheme.colorScheme. Cambiar al MatherialTheme Black todo
                )
            }
        }
    }

}