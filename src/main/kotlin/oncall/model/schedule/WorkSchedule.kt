package oncall.model.schedule

data class WorkSchedule(
    val month: Int,
    val day: Int,
    val dayOfWeek: String,
    val name: String,
)
