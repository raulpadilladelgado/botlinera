package botlinera.domain.valueobject

import java.lang.Math.PI
import java.lang.Math.cos

class Coordinates(val latitude: Double, val longitude: Double) {
    fun calculateMaximumCoordinates(maximumDistanceInMeters: Int): MaximumCoordinates {
        val earth = 6378.137
        val m = (1 / ((2 * PI / 360) * earth)) / 1000
        val maximumNorthCoordinate = latitude + (maximumDistanceInMeters * m)
        val maximumSouthCoordinate = latitude + (-maximumDistanceInMeters * m)
        val maximumEastCoordinate = longitude + (maximumDistanceInMeters * m) / cos(latitude * (PI / 180))
        val maximumWestCoordinate = longitude + (-maximumDistanceInMeters * m) / cos(latitude * (PI / 180))
        return MaximumCoordinates(
            maximumSouthCoordinate,
            maximumNorthCoordinate,
            maximumWestCoordinate,
            maximumEastCoordinate
        )
    }

}
