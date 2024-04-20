package com.example.myweatherapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myweatherapplication.DateUtils.toFormattedDay


@Preview
@Composable
fun preview(){
}
@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel,uiState: WeatherUiState){
    val weatherState by weatherViewModel.weatherState.collectAsState()

    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 90.dp, start = 15.dp, end = 10.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MainInfo(weatherState = weatherState,uiState)
        }
    }
}
@Composable
fun MainInfo(weatherState:WeatherState,uiState: WeatherUiState) {
    Column(
        modifier = Modifier.padding(top = 20.dp, end = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (weatherState) {
            is WeatherState.Success -> {
                val weatherData = weatherState.weatherData
                Text(
                    text = "${weatherData.location.name}, ${weatherData.location.country}",
                    fontSize = 30.sp,
                    color = Color(12, 47, 117, 255),
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = weatherData.current.condition.text,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(top = 10.dp)
                )
                AsyncImage(
                    modifier = Modifier.size(64.dp),
                    model = stringResource(id = R.string.icon_image_url,
                        uiState.weather?.condition?.icon.orEmpty()
                    ),
                    contentDescription = null
                )
                Text(
                    text = "${weatherData.current.temp_c}°C",
                    fontSize = 45.sp,
                    color = Color(0, 0, 139),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                )
                Text(
                    text ="",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0, 0, 139),
                )
                Spacer(modifier = Modifier.height(20.dp))

                    LazyColumn( modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(top = 8.dp, start = 16.dp),
                    ){
                        items(7){day->
                            ForecastComponents(
                                date = "2024-04-01",
                                icon = "113.png",
                                minTemp = ,
                                maxTemp =
                            )
                        }
                    }

            }
            is WeatherState.Loading -> {
                CircularProgressIndicator()
            }
            is WeatherState.Error -> {
                Text(
                    text = "Failed to fetch weather data.",
                    fontSize = 18.sp,
                    color = Color.Red
                )
            }
        }
    }
}

