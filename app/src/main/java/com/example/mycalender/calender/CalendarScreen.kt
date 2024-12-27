package com.example.mycalender.calender

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.yearMonth
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth


//month title
@Composable
fun CalenderScreen(viewModel: CalendarViewModel){
    val currentMonth = YearMonth.now()
    val startMonth = currentMonth.minusMonths(12)
    val endMonth = currentMonth.plusMonths(12)

    val selectedDate by viewModel.selectedDate.collectAsState()
    val eventsForSelectedDate by viewModel.eventsForSelectedDate.collectAsState()

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth
    )
    Column {
        HorizontalCalendar(
            state = state,
            dayContent = { day ->
                DayCell(day.date){
                    viewModel.onDateSelected(day.date)
                }
            },
            monthHeader = { month ->
                MonthHeader(month.yearMonth)
            },
            monthContainer = { month,content ->
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = "Month: ${month.yearMonth}",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(8.dp)
                    )
                    content()
                }
            }
        )
        Text(
            text = "Events for ${selectedDate}:",
            style = MaterialTheme.typography.titleLarge
        )

        LazyColumn {
            items(eventsForSelectedDate) { event ->
                Text(text = event.title)
            }
        }
    }

}

@Composable
fun DayCell(date: LocalDate,onClick: () -> Unit) {
    Text(
        text = date.dayOfMonth.toString(),
        modifier = Modifier
            .padding(8.dp)
            .clickable (onClick = onClick)
    )
}

@Composable
fun MonthHeader(yearMonth: YearMonth) {
    Text(
        text = "${yearMonth}",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .padding(16.dp)


    )
}