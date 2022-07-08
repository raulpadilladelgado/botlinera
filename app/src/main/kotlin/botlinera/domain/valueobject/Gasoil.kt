package botlinera.domain.valueobject

data class Gasoil constructor(
    var a: Double = 0.0,
    var b: Double = 0.0,
    var premium: Double = 0.0,
) {
    fun formatted(): String {
        var formatted = ""

        if (!a.isNaN()) {
            formatted += "\n - A: ${a}€"
        }

        if (!b.isNaN()) {
            formatted += "\n - B: ${b}€"
        }

        if (!premium.isNaN()) {
            formatted += "\n - Premium: ${premium}€"
        }

        return formatted
    }

    fun isNotEmpty(): Boolean {
        return !a.isNaN() || !b.isNaN() || !premium.isNaN()
    }
}
