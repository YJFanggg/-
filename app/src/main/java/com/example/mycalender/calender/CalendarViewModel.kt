package com.example.mycalender.calender

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycalender.data.Event
import com.example.mycalender.data.EventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDate

class CalendarViewModel(private val repository: EventRepository) : ViewModel() {

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate

    private val _eventsForSelectedDate = MutableStateFlow<List<Event>>(emptyList())
    val eventsForSelectedDate: StateFlow<List<Event>> = _eventsForSelectedDate

    init {
        loadEventsForDate(LocalDate.now())
    }

    fun onDateSelected(date: LocalDate) {
        _selectedDate.value = date
        loadEventsForDate(date)
    }

    private fun loadEventsForDate(date: LocalDate) {
        viewModelScope.launch {
            repository.getEventsByDate(date).collect { events ->
                _eventsForSelectedDate.value = events
            }
        }
    }

    fun addEvent(event: Event) {
        viewModelScope.launch {
            repository.insertEvent(event)
            loadEventsForDate(_selectedDate.value)
        }
    }

    fun deleteEvent(event: Event) {
        viewModelScope.launch {
            repository.deleteEvent(event)
            loadEventsForDate(_selectedDate.value)
        }
    }
}
