package oncall.utils

import oncall.model.Worker
import oncall.model.date.*
import oncall.model.schedule.WorkSchedule

object WorkScheduleGenerator {
    fun generateSchedule(
        workDate: WorkDate,
        weekDayWorkers: List<Worker>,
        weekendWorkers: List<Worker>
    )
            : List<WorkSchedule> {
        val month = workDate.month
        val schedule = mutableListOf<WorkSchedule>()
        val days = MonthRule.getDays(month)
        var weekdayWorkerIndex = 0
        var weekendWorkerIndex = 0
        var dayOfWeekIndex = DayOfWeek.DAYS.day.indexOf(workDate.firstDayWeek)
        var worker: Worker

        for (i in 1..days) {
            dayOfWeekIndex = IndexChecker.checkDayOfWeekIndex(dayOfWeekIndex)
            val dayOfWeek = DayOfWeek.DAYS.day[dayOfWeekIndex]
            when {
                PublicHoliday.isHoliday(month, i) || Weekend.isWeekend(dayOfWeek) -> {
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
        fixSchedule(schedule)
        return schedule
    }

    private fun fixSchedule(schedule: MutableList<WorkSchedule>) {
        for (i in schedule.indices) {
            if (i + 1 == schedule.lastIndex) {
                return
            }
            if (schedule[i].name == schedule[i + 1].name &&
                (!PublicHoliday.isHoliday(schedule[i].month, schedule[i].day) &&
                        !Weekend.isWeekend(schedule[i].dayOfWeek))
            ) {
                findNextWeekdayWorker(i + 1, schedule)
            }
            if (schedule[i].name == schedule[i + 1].name &&
                (PublicHoliday.isHoliday(schedule[i].month, schedule[i].day) ||
                        Weekend.isWeekend(schedule[i].dayOfWeek))
            ) {
                findNextHolidayWorker(i + 1, schedule)
            }
        }
    }

    private fun findNextHolidayWorker(index: Int, schedule: MutableList<WorkSchedule>) {
        for (i in index..<schedule.size) {
            if (PublicHoliday.isHoliday(schedule[i].month, schedule[i].day) ||
                Weekend.isWeekend(schedule[i].dayOfWeek)
            ) {
                val temp = schedule[index].name
                schedule[index].name = schedule[i].name
                schedule[i].name = temp
                return
            }
        }
    }

    private fun findNextWeekdayWorker(index: Int, schedule: MutableList<WorkSchedule>) {
        for (i in index..<schedule.size) {
            if (!PublicHoliday.isHoliday(schedule[i].month, schedule[i].day) &&
                !Weekend.isWeekend(schedule[i].dayOfWeek)
            ) {
                val temp = schedule[index].name
                schedule[index].name = schedule[i].name
                schedule[i].name = temp
                return
            }
        }
    }
}