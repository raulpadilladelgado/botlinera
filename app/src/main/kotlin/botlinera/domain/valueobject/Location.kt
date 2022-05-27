package botlinera.domain.valueobject

class Location constructor(
    val postalCode: String,
    val address: String,
    val time: String,
    val coordinates: Coordinates,
    val municipality: String,
    val province: String,
) {

}
