package com.example.mycalender.calender

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import java.time.LocalDate
import java.time.YearMonth

// 模擬數據類型，用於取代 LiveData
data class Reminder(
    val title: String,
    val date: String,
    val time: String,
    val note: String? = null
)

// 假設的靜態提醒數據
val remindersList = listOf(
    Reminder("Meeting", "2024-12-25", "10:00", "Christmas meeting"),
    Reminder("Workout", "2024-12-25", "18:00", "Gym session"),
    Reminder("Doctor", "2024-12-26", "15:00", "Regular checkup")
)

@Composable
fun CalendarScreen() {
    // 設定日曆的月份範圍
    val currentMonth = YearMonth.now()
    val startMonth = currentMonth.minusMonths(12)
    val endMonth = currentMonth.plusMonths(12)

    // 初始化日曆狀態
    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth
    )

    Column(modifier = Modifier.padding(16.dp)) {
        // 日曆顯示
        HorizontalCalendar(
            state = state,
            dayContent = { day ->
                DayCell(day.date, remindersList) // 傳遞靜態提醒列表
            },
            monthHeader = { month ->
                MonthHeader(month.yearMonth)
            }
        )

        // 顯示所有提醒列表
        Text(
            text = "所有提醒:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        remindersList.forEach { reminder ->
            Text(
                text = "${reminder.title} - ${reminder.date} ${reminder.time}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun DayCell(date: LocalDate, reminders: List<Reminder>) {
    val reminderForDay = reminders.filter { it.date == date.toString() } // 篩選當天提醒
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = date.dayOfMonth.toString()) // 日期
        reminderForDay.forEach { reminder ->
            Text(
                text = reminder.title,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun MonthHeader(yearMonth: YearMonth) {
    Text(
        text = "Header for: ${yearMonth}",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .padding(16.dp)
            .clickable {}
    )
}
