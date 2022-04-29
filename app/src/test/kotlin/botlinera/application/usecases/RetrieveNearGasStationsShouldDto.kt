package botlinera.application.usecases

import botlinera.domain.fixtures.valueobjects.GasStationFixtures
import botlinera.domain.fixtures.valueobjects.GasStationFixtures.Companion.aGasStation
import botlinera.domain.valueobject.Coordinates
import botlinera.domain.valueobject.GasStation
import org.junit.Test
import kotlin.test.assertEquals


class RetrieveNearGasStationsShouldDto {
    @Test
    fun retrieveGasStationsWithinFiveKilometers() {
        val latitude = "28.48204651183983".toBigDecimal()
        val longitude = "-16.31768990729595".toBigDecimal()
        val coordinates = Coordinates(latitude, longitude)

        val result = RetrieveNearGasStations().execute(coordinates)

        val expectedGasStations = listOf(
            aGasStation()
        )
        assertEquals(expectedGasStations, result)
    }
}
