package oncall.view

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun readDate(): List<String> {
        print(INPUT_DATE_MESSAGE)
        val input = Console.readLine()

        return input.split(",")
    }

    companion object {
        private const val INPUT_DATE_MESSAGE = "비상 근무를 배정할 월과 시작 요일을 입력하세요> "
    }
}