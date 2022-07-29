package botlinera.domain.valueobject

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CoordinatesShould {
    @Test
    fun calculateMaximumCoordinates() {
        val coordinates = Coordinates("28.0427319".toDouble(), "-16.7116703".toDouble())
        val maximumDistanceInMeters = 5000

        val maximumCoordinates = coordinates.calculateMaximumCoordinates(maximumDistanceInMeters)

        val expectedMaximumCoordinates = MaximumCoordinates(
            "27.997816135794025".toDouble(),
            "28.087647664205974".toDouble(),
            "-16.762560744378813".toDouble(),
            "-16.66077985562119".toDouble()
        )
        assertEquals(expectedMaximumCoordinates.maximumSouthCoordinate, maximumCoordinates.maximumSouthCoordinate)
        assertEquals(expectedMaximumCoordinates.maximumNorthCoordinate, maximumCoordinates.maximumNorthCoordinate)
        assertEquals(expectedMaximumCoordinates.maximumEastCoordinate, maximumCoordinates.maximumEastCoordinate)
        assertEquals(expectedMaximumCoordinates.maximumWestCoordinate, maximumCoordinates.maximumWestCoordinate)
    }
}
