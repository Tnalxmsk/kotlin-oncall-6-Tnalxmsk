package oncall.extension

fun String.isDuplicationName(): Boolean {
    val names = this.split(",")
    return names.size != names.toSet().size
}