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

    fun gas95AsText(): String{
        return prices.gas95.e5.toString()
    }
}
