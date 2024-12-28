// CalendarScreen.kt
@file:OptIn(ExperimentalFoundationApi::class)
package com.example.mycalender.calender

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.example.mycalender.data.Event
import java.time.LocalDate
import java.time.YearMonth
import java.util.Locale

@Composable
fun CalenderScreen(viewModel: CalendarViewModel) {
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

    // Variables for delete dialog
    var showDeleteDialog by remember { mutableStateOf(false) }
    var eventToDelete by remember { mutableStateOf<Event?>(null) }

    Column {
        HorizontalCalendar(
            state = state,
            dayContent = { day ->
                DayCell(day.date) {
                    viewModel.onDateSelected(day.date)
                }
            },
            monthContainer = { month, content ->
                Column(modifier = Modifier.padding(8.dp)) {
                    val monthName = "${month.yearMonth.month.name.capitalize(Locale.ROOT)} ${month.yearMonth.year}"
                    Text(
                        text = monthName,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(8.dp)
                    )
                    content()
                }
            }
        )

        Text(
            text = "Events for ${selectedDate}:",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
        )

        if (eventsForSelectedDate.isEmpty()) {
            Text(
                text = "No itinerary planned today",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(8.dp)
            )
        } else {
            LazyColumn(modifier = Modifier.padding(8.dp)) {
                items(eventsForSelectedDate) { event ->
                    Text(
                        text = event.title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .combinedClickable(
                                onClick = {},
                                onLongClick = {
                                    showDeleteDialog = true
                                    eventToDelete = event
                                }
                            )
                    )
                }
            }
        }

        if (showDeleteDialog && eventToDelete != null) {
            DeleteEventDialog(
                eventTitle = eventToDelete!!.title,
                onConfirmDelete = {
                    viewModel.deleteEvent(eventToDelete!!)
                    showDeleteDialog = false
                },
                onCancel = {
                    showDeleteDialog = false
                }
            )
        }
    }
}

@Composable
fun DayCell(date: LocalDate, onClick: () -> Unit) {
    Text(
        text = date.dayOfMonth.toString(),
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    )
}
