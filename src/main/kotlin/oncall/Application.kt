package oncall

import oncall.model.WorkAssignmentServiceApp
import oncall.view.InputView
import oncall.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val workAssignmentServiceApp = WorkAssignmentServiceApp(inputView, outputView)

    workAssignmentServiceApp.startService()
}
