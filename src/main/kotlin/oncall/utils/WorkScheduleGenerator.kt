package oncall.utils

import oncall.model.Worker
import oncall.model.date.*
import oncall.model.schedule.WorkSchedule

object WorkScheduleGenerator {
    fun generateSchedule(
        workDate: WorkDate,
        weekDayWorkers: List<Worker>,
        weekendWorkers: List<Worker>)
    : List<WorkSchedule> {
        val month = workDate.month
        val schedule = mutableListOf<WorkSchedule>()
        val days = MonthRule.getDays(month)
        var weekdayWorkerIndex = 0
        var weekendWorkerIndex = 0
        var dayOfWeekIndex = DayOfWeek.DAYS.day.indexOf(workDate.firstDayWeek)

        for (i in 1..days) {
            var worker: Worker
            dayOfWeekIndex = IndexChecker.checkDayOfWeekIndex(dayOfWeekIndex)
            val dayOfWeek = DayOfWeek.DAYS.day[dayOfWeekIndex]
            when {
                PublicHoliday.isHoliday(month ,i) || Weekend.isWeekend(dayOfWeek) -> {
                    weekendWorkerIndex = IndexChecker.checkIndex(weekendWorkerIndex, weekendWorkers)
                    worker = weekendWorkers[weekendWorkerIndex]
                    weekendWorkerIndex++
                    dayOfWeekIndex++
                    schedule.add(WorkSchedule(month, i, dayOfWeek, worker.name))
                }
                else -> {
                    weekdayWorkerIndex = IndexChecker.checkIndex(weekdayWorkerIndex, weekDayWorkers)
                    worker = weekDayWorkers[weekdayWorkerIndex]
                    weekdayWorkerIndex++
                    dayOfWeekIndex++
                    schedule.add(WorkSchedule(month, i, dayOfWeek, worker.name))
                }
            }
        }
        return schedule
    }
}