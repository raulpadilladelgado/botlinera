package botlinera.application.usecases

import botlinera.application.ports.GastStationPersister
import botlinera.domain.fixtures.valueobjects.GasStationFixtures.Companion.multipleGasStationsWithinAFiveKilometersRadius
import botlinera.domain.valueobject.*
import botlinera.domain.valueobject.GasType.*
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class NearGasStationShould {
    @Test
    fun getNearGasStations() {
        val coordinates = Coordinates("28.0427319".toDouble(), "-16.7116703".toDouble())
        val maximumDistanceInMeters = 5000
        val gasStationsPersister = mockk<GastStationPersister>()
        val expectedGasStations = multipleGasStationsWithinAFiveKilometersRadius()
        every { gasStationsPersister.queryNearGasStations(any(), GASOLINA_95_E5) } returns expectedGasStations
        val gasStations = NearGasStation(gasStationsPersister).execute(
            coordinates,
            maximumDistanceInMeters,
            GASOLINA_95_E5
        )
        assertEquals(expectedGasStations, gasStations)
    }
}
