package botlinera.domain.valueobject

class GasStation (
    val name: String,
    val location: Location,
    val prices: Prices
) {
    fun latitudeAsText(): String{
        return location.coordinates.latitude.toString()
    }

    fun longitudeAsText(): String{
        return location.coordinates.longitude.toString()
    }

    fun gas95AsText(): String{
        return prices.gas95.e5.toString()
    }
}
