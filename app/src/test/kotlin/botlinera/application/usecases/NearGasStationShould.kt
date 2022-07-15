package botlinera.application.usecases

import botlinera.application.exceptions.FailedToRetrieveNearGasStations
import botlinera.application.ports.GasStationPersister
import botlinera.domain.fixtures.valueobjects.GasStationFixtures.Companion.multipleGasStationsWithinAFiveKilometersRadius
import botlinera.domain.valueobject.*
import botlinera.domain.valueobject.GasType.*
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import kotlin.test.assertFailsWith

@ExtendWith(MockKExtension::class)
class NearGasStationShould {
    @MockK
    private lateinit var gasStationsPersister: GasStationPersister

    @Test
    fun `retrieve near gas stations`() {
        val coordinates = Coordinates("28.0427319".toDouble(), "-16.7116703".toDouble())
        val maximumDistanceInMeters = 5000
        val expectedGasStations = multipleGasStationsWithinAFiveKilometersRadius()
        every { gasStationsPersister.queryNearGasStations(any(), GASOLINA_95_E5) } returns Result.success(expectedGasStations)

        val gasStations = NearGasStation(gasStationsPersister).execute(
            coordinates,
            maximumDistanceInMeters,
            GASOLINA_95_E5
        )

        assertEquals(expectedGasStations, gasStations)
    }

    @Test
    fun `raise an error when fails retrieving near gas stations`() {
        every { gasStationsPersister.queryNearGasStations(any(), any()) } returns Result.failure(FailedToRetrieveNearGasStations(RuntimeException()))

        assertFailsWith<FailedToRetrieveNearGasStations> {
            NearGasStation(gasStationsPersister).execute(
                any(),
                any(),
                any()
            )
        }
    }
}
