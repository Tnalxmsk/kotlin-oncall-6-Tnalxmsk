package oncall.utils

import oncall.model.Worker
import oncall.model.date.DayOfWeek

object IndexChecker {
    fun checkIndex(index: Int, workers: List<Worker>): Int {
        return if (index > workers.lastIndex) 0 else index
    }

    fun checkDayOfWeekIndex(index: Int): Int {
        return if (index > DayOfWeek.DAYS.day.lastIndex) 0 else index
    }
}