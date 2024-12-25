package com.example.mycalender.calender

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalendarViewModel(application: Application) : AndroidViewModel(application) {

    // 獲取 ReminderDao 實例
    private val reminderDao: ReminderDao = AppDatabase.getInstance(application).reminderDao()

    // LiveData 用於觀察所有提醒數據
    val allReminders: LiveData<List<Reminder>> = reminderDao.getAllReminders()

    // 插入提醒
    fun insertReminder(reminder: Reminder) {
        viewModelScope.launch(Dispatchers.IO) {
            reminderDao.insertReminder(reminder)
        }
    }

    // 更新提醒
    fun updateReminder(reminder: Reminder) {
        viewModelScope.launch(Dispatchers.IO) {
            reminderDao.updateReminder(reminder)
        }
    }

    // 刪除提醒
    fun deleteReminder(reminder: Reminder) {
        viewModelScope.launch(Dispatchers.IO) {
            reminderDao.deleteReminder(reminder)
        }
    }
}
