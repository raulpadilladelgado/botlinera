package botlinera.domain.valueobject

class GasStation(
    val name: String,
    val location: Location,
    val prices: Prices
) {
    fun latitude(): Double {
        return location.coordinates.latitude
    }

    fun longitude(): Double {
        return location.coordinates.longitude
    }

    fun formatted(): String {
        return buildString {
            append("⛽️ $name\n")
            append("🕐 ${location.time}")
            append(prices.formatted())
        }
    }
}
