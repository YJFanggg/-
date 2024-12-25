package com.example.mycalender.calender

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class Reminder(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // 必須加上 @PrimaryKey
    val title: String, // 提醒標題
    val date: String, // 提醒日期
    val time: String, // 提醒時間
    val note: String? = null // 備註（可為空）
)
