package com.example.mycalender.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val note: String? = null,
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime
)