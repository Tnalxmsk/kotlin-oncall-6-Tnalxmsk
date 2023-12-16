package oncall.view

import oncall.model.date.PublicHoliday
import oncall.model.schedule.WorkSchedule

class OutputView {
    fun printWorkSchedule(workSchedule: List<WorkSchedule>) {
        println()
        workSchedule.forEach {
            if (PublicHoliday.isHoliday(it.month, it.day)) {
                println(OUTPUT_HOLIDAY_FORMAT.format(it.month, it.day, it.dayOfWeek, it.name))

            } else println(OUTPUT_SCHEDULE_FORMAT.format(it.month, it.day, it.dayOfWeek, it.name))
        }
    }

    companion object {
        private const val OUTPUT_HOLIDAY_FORMAT = "%d월 %d일 %s(휴일) %s"
        private const val OUTPUT_SCHEDULE_FORMAT = "%d월 %d일 %s %s"
    }
}