package botlinera.domain.valueobject

class Location(
    val postalCode: String,
    val address: String,
    val time: String,
    val coordinates: Coordinates,
    val municipality: String,
    val province: String,
    val locality: String,
) {
    fun latitude() = coordinates.latitude
    fun longitude() = coordinates.longitude
}
