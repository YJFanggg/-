package com.example.mycalender.calender

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mycalender.calender.Reminder


@Dao
interface ReminderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: Reminder)

    @Update
    suspend fun updateReminder(reminder: Reminder)

    @Delete
    suspend fun deleteReminder(reminder: Reminder)

    @Query("SELECT * FROM reminders ORDER BY date, time")
    fun getAllReminders(): LiveData<List<Reminder>>
}
