package oncall.validation

import oncall.extension.isDuplicationName

enum class InputValidator(val message: String) {
    INVALID_VALUE("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");

    companion object {
        private const val SPLIT_DELIMITERS = ","
        private const val GAP = " "

        fun validateInputDate(input: String) {
            val error = when {
                input.isEmpty() -> INVALID_VALUE
                input.contains(GAP) -> INVALID_VALUE
                input.contains(SPLIT_DELIMITERS).not() -> INVALID_VALUE
                input.split(SPLIT_DELIMITERS).first().matches(Regex("\\d+")).not() -> INVALID_VALUE
                input.split(SPLIT_DELIMITERS).first() == "0" -> INVALID_VALUE
                else -> return
            }
            throw IllegalArgumentException(error.message)
        }

        fun validInputNames(input: String) {
            val error = when {
                input.isEmpty() -> INVALID_VALUE
                input.contains(GAP) -> INVALID_VALUE
                input.isDuplicationName() -> INVALID_VALUE
                else -> return
            }
            throw IllegalArgumentException(error.message)
        }
    }
}