package oncall.model

import oncall.model.date.*

object WorkScheduleGenerator {
    fun generateSchedule(
        workDate: WorkDate,
        weekDayWorkers: List<Worker>,
        weekendWorkers: List<Worker>)
    : List<WorkSchedule> {
        val month = workDate.month
        val schedule = mutableListOf<WorkSchedule>()
        val days = MonthRole.getDays(month)
        var weekdayWorkerIndex = 0
        var weekendWorkerIndex = 0
        var dayOfWeekIndex = DayOfWeek.DAYS.day.indexOf(workDate.firstDayWeek)

        for (i in 1..days) {
            var worker: Worker
            dayOfWeekIndex = checkDayOfWeekIndex(dayOfWeekIndex)
            val dayOfWeek = DayOfWeek.DAYS.day[dayOfWeekIndex]
            when {
                PublicHoliday.isHoliday(month ,i) || Weekend.isWeekend(dayOfWeek) -> {
                    weekendWorkerIndex = checkIndex(weekendWorkerIndex, weekendWorkers)
                    worker = weekendWorkers[weekendWorkerIndex]
                    weekendWorkerIndex++
                    dayOfWeekIndex++
                    schedule.add(WorkSchedule(month, i, dayOfWeek, worker.name))
                }
                else -> {
                    weekdayWorkerIndex = checkIndex(weekdayWorkerIndex, weekDayWorkers)
                    worker = weekDayWorkers[weekdayWorkerIndex]
                    weekdayWorkerIndex++
                    dayOfWeekIndex++
                    schedule.add(WorkSchedule(month, i, dayOfWeek, worker.name))
                }
            }
        }
        return schedule
    }

    private fun checkIndex(index: Int, workers: List<Worker>): Int {
        return if (index > workers.lastIndex) 0 else index
    }

    private fun checkDayOfWeekIndex(index: Int): Int {
        return if (index > DayOfWeek.DAYS.day.lastIndex) 0 else index
    }
}