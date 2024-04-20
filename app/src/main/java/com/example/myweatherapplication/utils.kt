package com.example.myweatherapplication

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import java.util.Locale

@Preview
@Composable
fun utils() {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(top = 8.dp, start = 16.dp),
    ) {
//        uiState.weather?.let { weather ->
//            items(weather.forecasts) { forecast ->
//                ForecastComponent(
//                    date = forecast.date,
//                    icon = forecast.icon,
//                    minTemp = stringResource(
//                        R.string.temperature_value_in_celsius,
//                        forecast.minTemp
//                    ),
//                    maxTemp = stringResource(
//                        R.string.temperature_value_in_celsius,
//                        forecast.maxTemp,
//                    ),
//                )
//            }
//        }
    }
}

@Composable
fun ForecastComponent(
    modifier: Modifier = Modifier,
    date: String,
    icon: String,
    minTemp: String,
    maxTemp: String,
) {
    ElevatedCard(
        modifier = modifier.padding(end = 16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                text ="date.toFormattedDay().orEmpty()",
                style = MaterialTheme.typography.titleMedium
            )
            AsyncImage(
                modifier = Modifier.size(42.dp),
                model = stringResource(R.string.icon_image_url, icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable._1),
                placeholder = painterResource(id = R.drawable._1),
            )
            Text(
                text = maxTemp,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.width(4.dp))

            Text(
                text = minTemp, style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ForecastComponentPreview() {
    Surface {
            ForecastComponent(
                date = "2023-10-07",
                icon = "116.png",
                minTemp = "12",
                maxTemp = "28",
            )
        }
    }