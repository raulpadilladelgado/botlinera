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
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertFailsWith

private const val MAXIMUM_DISTANCE_IN_METERS = 5000

@ExtendWith(MockKExtension::class)
class RetrieveNearGasStationShould {
    @MockK
    private lateinit var gasStationsPersister: GasStationPersister

    private lateinit var retrieveNearGasStation: RetrieveNearGasStation


    @BeforeEach
    internal fun setUp() {
        retrieveNearGasStation = RetrieveNearGasStation(gasStationsPersister)
    }


    @Test
    fun `retrieve near gas stations`() {
        val expectedGasStations = multipleGasStationsWithinAFiveKilometersRadius()
        every { gasStationsPersister.queryNearGasStations(any(), GASOLINA_95_E5) } returns Result.success(expectedGasStations)

        val gasStations = retrieveNearGasStation.execute(
            someCoordinates(),
            MAXIMUM_DISTANCE_IN_METERS,
            GASOLINA_95_E5
        )

        assertEquals(expectedGasStations, gasStations)
    }

    @Test
    fun `raise an error if failing to retrieve near gas stations`() {
        every { gasStationsPersister.queryNearGasStations(any(), GASOLINA_95_E5) } returns Result.failure(RuntimeException())

        assertFailsWith<FailedToRetrieveNearGasStations> {
            retrieveNearGasStation.execute(
                someCoordinates(),
                MAXIMUM_DISTANCE_IN_METERS,
                GASOLINA_95_E5
            )
        }
    }

    private fun someCoordinates() = Coordinates("28.0427319".toDouble(), "-16.7116703".toDouble())
}
