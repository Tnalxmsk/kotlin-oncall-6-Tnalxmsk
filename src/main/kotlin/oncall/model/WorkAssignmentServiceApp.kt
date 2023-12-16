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
        val holidayWorkers = createWeekdayWorker()
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
}