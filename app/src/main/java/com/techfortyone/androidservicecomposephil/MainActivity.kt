package com.techfortyone.androidservicecomposephil

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.techfortyone.androidservicecomposephil.service.RunningService
import com.techfortyone.androidservicecomposephil.ui.theme.AndroidServiceComposePhilTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!hasNotificationPermission()) {
            requestPermissions()
        } else {

        }
        setContent {
            AndroidServiceComposePhilTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            Intent(applicationContext,RunningService::class.java).also {
                                it.action = RunningService.Actions.START.toString()
                                startService(it)
                            }
                        }) {
                            Text(
                                text = "Start Service",
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))

                        Button(onClick = {
                            Intent(applicationContext,RunningService::class.java).also {
                                it.action = RunningService.Actions.STOP.toString()
                                startService(it)
                            }
                        }) {
                            Text(
                                text = "Stop Service",
                            )
                        }
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun hasNotificationPermission() = ActivityCompat.checkSelfPermission(
        this, Manifest.permission.POST_NOTIFICATIONS
    ) == PackageManager.PERMISSION_GRANTED

    fun requestPermissions() {
        val permissionsToRequest = mutableListOf<String>()
        permissionsToRequest.add(Manifest.permission.POST_NOTIFICATIONS)
        ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(), 101)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidServiceComposePhilTheme {
        Greeting("Android")
    }
}