package com.example.mycalender

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycalender.calender.AddEventScreen
import com.example.mycalender.ui.theme.MyCalenderTheme
import com.example.mycalender.calender.CalenderScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCalenderTheme {
                MainActivityContent()

            }
        }
    }
}

@Composable
fun MainActivityContent(){
    var showAddEventScreen by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddEventScreen = true }) {
                Text(text = "+")
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            if (showAddEventScreen) {
                AddEventScreen(
                    onEventAdded = { showAddEventScreen = false } // 新增完成後回到行事曆畫面
                )
            } else {
                CalenderScreen()
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CalendarPreview() {
    MyCalenderTheme {
        MainActivityContent()
    }
}