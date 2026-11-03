package com.example.countdowntimer

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.countdowntimer.ui.theme.CountdownTimerTheme
import kotlin.jvm.java

const val TIMER_KEY = "timerKey"


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CountdownTimerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TimerComposable(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TimerComposable(modifier: Modifier = Modifier) {

    Column(
        Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter starting countdown value!",
            modifier = modifier
        )
        val context= LocalContext.current
        var timerValue by rememberSaveable() { mutableStateOf("") }


        TextField(
            value = "",
            onValueChange = { timerValue = it },

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button({
            context.startService(
                Intent(
                    context,
                    MyService::class.java
                ).apply {
                    putExtra(TIMER_KEY, timerValue.toInt(10))
                }
            ) }
        )
        {}
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CountdownTimerTheme {
    }
}