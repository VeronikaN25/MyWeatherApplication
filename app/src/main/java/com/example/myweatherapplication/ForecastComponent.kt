package com.example.myweatherapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myweatherapplication.DateUtils.toFormattedDay
import com.example.myweatherapplication.ui.theme.MyWeatherApplicationTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun ForecastComponents(
    date: String,
    icon: String,
    minTemp: String,
    maxTemp: String,
){
    val formattedDate = remember { formatDateString(date) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = formattedDate,
            modifier = Modifier.padding(start = 4.dp, end = 4.dp),
            style = MaterialTheme.typography.titleMedium
        )
        AsyncImage(
            model = stringResource(id = R.string.icon_image_url,icon),
            contentDescription =null ,
            error= painterResource(id = R.drawable._1),
            placeholder =  painterResource(id = R.drawable._1),
        )
        Text(
            text = maxTemp,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = minTemp, style = MaterialTheme.typography.bodySmall
        )
    }
}
@Preview
@Composable
fun ForecastComponentPrevie(){
    Surface {
        MyWeatherApplicationTheme {
            ForecastComponents(
                date = "2024-04-01",
                icon = "116.png",
                minTemp = "12",
                maxTemp = "28",
            )
        }
    }
}
fun formatDateString(dateString: String): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = formatter.parse(dateString) ?: Date()
    val calendar = Calendar.getInstance()
    calendar.time = date
    val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())
    return dayOfWeek ?: ""
}