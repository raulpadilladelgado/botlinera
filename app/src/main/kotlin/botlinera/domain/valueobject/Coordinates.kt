package botlinera.domain.valueobject

import java.lang.Math.PI
import java.lang.Math.cos

class Coordinates(val latitude: Double, val longitude: Double) {
    fun calculateMaximumCoordinates(): MaximumCoordinates {
        val earth = 6378.137
        val m = (1 / ((2 * PI / 360) * earth)) / 1000
        val maximumNorthCoordinate = latitude + (5000 * m)
        val maximumSouthCoordinate = latitude + (-5000 * m)
        val maximumEastCoordinate = longitude + (5000 * m) / cos(latitude * (PI / 180))
        val maximumWestCoordinate = longitude + (-5000 * m) / cos(latitude * (PI / 180))
        return MaximumCoordinates(
            maximumSouthCoordinate,
            maximumNorthCoordinate,
            maximumWestCoordinate,
            maximumEastCoordinate
        )
    }

}
