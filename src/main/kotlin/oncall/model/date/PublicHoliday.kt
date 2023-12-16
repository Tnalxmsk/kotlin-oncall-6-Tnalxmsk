package oncall.model.date

enum class PublicHoliday(val month: Int, val day: Int) {
    NEW_YEAR(1, 1),
    MARCH_FIRST(3, 1),
    KIDS_DAY(5, 5),
    MEMORIAL_DAY(6, 6),
    LIBERATION_DAY(8, 15),
    NATIONAL_FOUNDATION_DAY(10, 3),
    HANGEUL_DAY(10, 9),
    CHRISTMAS(12, 25)
}