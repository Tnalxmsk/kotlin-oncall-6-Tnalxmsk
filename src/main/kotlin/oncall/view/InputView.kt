package oncall.view

import camp.nextstep.edu.missionutils.Console
import oncall.validation.InputValidator

class InputView {
    fun readDate(): List<String> {
        try {
            print(INPUT_DATE_MESSAGE)
            val input = Console.readLine()
            InputValidator.validateInputDate(input)
            return input.split(SPLIT_DELIMITERS)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            return readDate()
        }
    }

    fun readWeekdayWorkersName(): List<String> {
        try {
            print(INPUT_WEEKDAY_WORKER_NAME_MESSAGE)
            val input = Console.readLine()
            InputValidator.validInputNames(input)
            return input.split(SPLIT_DELIMITERS)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            return readWeekdayWorkersName()
        }
    }

    fun readHolidayWorkersName(): List<String> {
        try {
            print(INPUT_HOLIDAY_WORKER_NAME_MESSAGE)
            val input = Console.readLine()
            return input.split(SPLIT_DELIMITERS)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            return  readHolidayWorkersName()
        }
    }

    companion object {
        private const val SPLIT_DELIMITERS = ","
        private const val INPUT_DATE_MESSAGE = "비상 근무를 배정할 월과 시작 요일을 입력하세요> "
        private const val INPUT_WEEKDAY_WORKER_NAME_MESSAGE = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> "
        private const val INPUT_HOLIDAY_WORKER_NAME_MESSAGE = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> "
    }
}