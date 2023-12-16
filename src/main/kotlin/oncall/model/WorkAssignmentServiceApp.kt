package oncall.model

import oncall.view.InputView
import oncall.view.OutputView

class WorkAssignmentServiceApp(
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun startService() {
        val workDate = createWorkDate()
    }

    private fun createWorkDate(): WorkDate {
        val date = inputView.readDate()
        val month = date[0].toInt()
        val dayOfWeek = date[1]
        return WorkDate(month, dayOfWeek)
    }
}