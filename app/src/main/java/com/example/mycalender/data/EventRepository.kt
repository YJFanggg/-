package com.example.mycalender.data

import java.time.LocalDate

class EventRepository(private val eventDao: EventDao) {

    fun getEventsByDate(date: LocalDate) = eventDao.getEventsByDate(date)

    //fun getAllEvents() = eventDao.getAllEvents()

    suspend fun insertEvent(event: Event) {
        eventDao.insertEvent(event)
    }

    suspend fun deleteEvent(event: Event) {
        eventDao.deleteEvent(event)
    }
}