package com.example.mycalender.calender

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycalender.data.Event
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun AddEventScreen(
    onEventAdded: () -> Unit,
    onCancel: () -> Unit = {},
    addEventToDatabase: (Event) -> Unit
) {
    val context = LocalContext.current

    // 表單狀態
    var eventName by remember { mutableStateOf("") }
    var eventNote by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var startTime by remember { mutableStateOf(LocalTime.now()) }
    var endTime by remember { mutableStateOf(LocalTime.now().plusHours(1)) }

    // 日期選擇器
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
        },
        selectedDate.year,
        selectedDate.monthValue - 1,
        selectedDate.dayOfMonth
    )

    // 開始時間選擇器
    val startTimePickerDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            startTime = LocalTime.of(hourOfDay, minute)
        },
        startTime.hour,
        startTime.minute,
        true
    )

    // 結束時間選擇器
    val endTimePickerDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            endTime = LocalTime.of(hourOfDay, minute)
        },
        endTime.hour,
        endTime.minute,
        true
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // 標題
        Text(
            text = "新增行程",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // 行程名稱
        OutlinedTextField(
            value = eventName,
            onValueChange = { eventName = it },
            label = { Text("行程名稱") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 行程備註
        OutlinedTextField(
            value = eventNote,
            onValueChange = { eventNote = it },
            label = { Text("行程備註") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 日期選擇
        Button(
            onClick = { datePickerDialog.show() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "選擇日期：${selectedDate}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 開始時間選擇
        Button(
            onClick = { startTimePickerDialog.show() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "開始時間：${startTime}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 結束時間選擇
        Button(
            onClick = { endTimePickerDialog.show() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "結束時間：${endTime}")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 動作按鈕
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = onCancel, modifier = Modifier.weight(1f)) {
                Text(text = "取消")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    if (eventName.isBlank()) {
                        Toast.makeText(context, "行程名稱不能為空", Toast.LENGTH_SHORT).show()
                    } else if (endTime <= startTime) {
                        Toast.makeText(context, "結束時間必須晚於開始時間", Toast.LENGTH_SHORT).show()
                    } else {
                        // 新增行程到資料庫
                        val newEvent = Event(
                            title = eventName,
                            note = eventNote,
                            date = selectedDate,
                            startTime = startTime,
                            endTime = endTime
                        )
                        addEventToDatabase(newEvent)
                        Toast.makeText(context, "行程已新增", Toast.LENGTH_SHORT).show()
                        onEventAdded()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "完成")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun AddEventScreenPreview() {
}
