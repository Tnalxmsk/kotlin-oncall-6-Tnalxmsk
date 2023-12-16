package oncall.view

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun readDate(): List<String> {
        print(INPUT_DATE_MESSAGE)
        val input = Console.readLine()

        return input.split(SPLIT_DELIMITERS)
    }

    fun readWeekdayWorkersName(): List<String> {
        print(INPUT_WEEKDAY_WORKER_NAME_MESSAGE)
        val input = Console.readLine()
        return input.split(SPLIT_DELIMITERS)
    }

    companion object {
        private const val SPLIT_DELIMITERS = ","
        private const val INPUT_DATE_MESSAGE = "비상 근무를 배정할 월과 시작 요일을 입력하세요> "
        private const val INPUT_WEEKDAY_WORKER_NAME_MESSAGE = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> "
    }
}