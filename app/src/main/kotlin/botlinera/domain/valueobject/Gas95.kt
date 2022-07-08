package botlinera.domain.valueobject

data class Gas95 constructor(
    val e10: Double = 0.0,
    val e5: Double = 0.0,
    val e5Premium: Double = 0.0
) {
    fun formatted(): String {
        var formatted = ""

        if (!e5.isNaN()) {
            formatted += "\n - 95 E5: ${e5}€"
        }

        if (!e10.isNaN()) {
            formatted += "\n - 95 E10: ${e10}€"
        }

        if (!e5Premium.isNaN()) {
            formatted += "\n - 95 E5 Premium: ${e5Premium}€"
        }

        return formatted
    }

    fun isNotEmpty(): Boolean {
        return !e5.isNaN() || !e10.isNaN() || !e5Premium.isNaN()
    }
}

