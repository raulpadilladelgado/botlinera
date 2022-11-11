package botlinera.domain.valueobject

data class GasStation(
    val name: String,
    val location: Location,
    val prices: Prices
) {
    fun latitude() = location.latitude()

    fun longitude() = location.longitude()

    fun postalCode() = location.postalCode

    fun address() = location.address

    fun time() = location.time

    fun locality() = location.locality

    fun municipality() = location.municipality

    fun province() = location.province

    fun formatted() = buildString {
        append("⛽️ $name\n")
        append("📍 ${location.locality}\n")
        append("🏡 ${location.municipality}\n")
        append("🕐 ${location.time}")
        append(prices.formatted())
    }
}
