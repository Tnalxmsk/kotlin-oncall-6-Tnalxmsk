package oncall.model.date

enum class Weekend(val day: String) {
    SATURDAY("토"),
    SUNDAY("일");

    companion object {
        fun isWeekend(inputDay: String): Boolean {
            entries.forEach {
                if (it.day == inputDay) {
                    return true
                }
            }
            return false
        }
    }
}