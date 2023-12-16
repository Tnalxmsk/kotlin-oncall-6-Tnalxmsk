package oncall.model.date

enum class MonthRole(val month: List<Int>, val days: Int) {
    THIRTY_FIRST(listOf(1, 3, 5, 7, 8, 10, 12), 31),
    THIRTIETH(listOf(4, 6, 9, 11), 30),
    TWENTY_EIGHTH(listOf(2), 28);

    companion object {
        fun getDays(month: Int): Int {
            return MonthRole.entries.find { it.month.contains(month) }!!.days
        }
    }
}