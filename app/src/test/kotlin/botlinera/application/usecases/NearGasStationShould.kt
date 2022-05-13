package botlinera.application.usecases

import botlinera.application.ports.GastStationPersister
import botlinera.domain.fixtures.valueobjects.GasStationFixtures.Companion.multipleGasStationsWithinAFiveKilometersRadius
import botlinera.domain.valueobject.Coordinates
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import kotlin.test.assertEquals

class NearGasStationShould {
    @Test
    fun getNearGasStations() {
        val coordinates = Coordinates("28.0427319".toDouble(),"-16.7116703".toDouble())
        val gasStationsPersister = mockk<GastStationPersister>()
        val expectedGasStations = multipleGasStationsWithinAFiveKilometersRadius()
        every { gasStationsPersister.queryNearGasStations(any()) } returns expectedGasStations

        val gasStations = NearGasStation(gasStationsPersister).execute(coordinates)

        assertEquals(expectedGasStations, gasStations)
    }

}
