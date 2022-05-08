package botlinera.application.usecases

import botlinera.application.ports.GastStationPersister
import botlinera.domain.fixtures.valueobjects.GasStationFixtures.Companion.aGasStation
import botlinera.domain.valueobject.Coordinates
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertEquals


class RetrieveNearGasStationsShould {
    @Test
    fun retrieveGasStationsWithinFiveKilometers() {
        val latitude = "28.0427319".toDouble()
        val longitude = "-16.7116703".toDouble()
        val coordinates = Coordinates(latitude, longitude)
        val fakeGasStationsPersister = Mockito.mock(GastStationPersister::class.java)

        val result = RetrieveNearGasStations(fakeGasStationsPersister).execute(coordinates)

        val expectedGasStations = listOf(
            aGasStation()
        )
        assertEquals(expectedGasStations, result)
    }
}
