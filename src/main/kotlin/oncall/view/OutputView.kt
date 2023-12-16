package oncall.view

import oncall.model.schedule.WorkSchedule

class OutputView {
    fun printWorkSchedule(workSchedule: List<WorkSchedule>) {
        workSchedule.forEach {
            println(OUTPUT_SCHEDULE_FORMAT.format(it.month, it.day, it.dayOfWeek, it.name))
        }
    }

    companion object {
        private const val OUTPUT_SCHEDULE_FORMAT = "%d월 %d일 %s %s"
    }
}