package botlinera.domain.valueobject

data class Gas98 constructor(
    val e10: Double = 0.0,
    val e5: Double = 0.0
) {
    fun formatted(): String {
        var formatted = ""

        if (!e5.isNaN()) {
            formatted += "\n - 98 E5: ${e5}€"
        }

        if (!e10.isNaN()) {
            formatted += "\n - 98 E10: ${e10}€"
        }
        return formatted
    }

    fun isNotEmpty(): Boolean {
        return !e5.isNaN() || !e10.isNaN()
    }
}

