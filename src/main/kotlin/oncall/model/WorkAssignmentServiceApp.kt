package oncall.model

import oncall.model.date.WorkDate
import oncall.view.InputView
import oncall.view.OutputView

class WorkAssignmentServiceApp(
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun startService() {
        val workDate = createWorkDate()
        val weekdayWorkers = createWeekdayWorker()
        val holidayWorkers = createHolidayWorker()
        val workSchedule = createAssignmentSchedule(workDate, weekdayWorkers, holidayWorkers)
        outputView.printWorkSchedule(workSchedule)
    }

    private fun createWorkDate(): WorkDate {
        val date = inputView.readDate()
        val month = date[0].toInt()
        val dayOfWeek = date[1]
        return WorkDate(month, dayOfWeek)
    }

    private fun createWeekdayWorker(): List<Worker> {
        return inputView.readWeekdayWorkersName().map { Worker(it) }
    }

    private fun createHolidayWorker(): List<Worker> {
        return inputView.readHolidayWorkersName().map { Worker(it) }
    }

    private fun createAssignmentSchedule(
        workDate: WorkDate,
        weekDayWorkers: List<Worker>,
        holidayWorkers: List<Worker>
    ): List<WorkSchedule> {
        return WorkScheduleGenerator.generateSchedule(workDate, weekDayWorkers, holidayWorkers)
    }
}