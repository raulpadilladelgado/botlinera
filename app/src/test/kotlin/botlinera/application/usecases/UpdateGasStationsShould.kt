package botlinera.application.usecases

import botlinera.application.exceptions.FailedToReplaceGasStations
import botlinera.application.exceptions.FailedToRetrieveGasStations
import botlinera.application.exceptions.FailedToUpdateGasStation
import botlinera.application.ports.GasStationPersister
import botlinera.application.ports.GasStationsRetriever
import botlinera.domain.fixtures.valueobjects.GasStationFixtures.Companion.multipleGasStationsWithinAFiveKilometersRadius
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verifyOrder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertFailsWith

@ExtendWith(MockKExtension::class)
class UpdateGasStationsShould {
    @MockK
    private lateinit var gasStationsRetriever: GasStationsRetriever
    @RelaxedMockK
    private lateinit var gasStationsPersister: GasStationPersister
    private lateinit var updateGasStations: UpdateGasStations
    private val someGasStations = multipleGasStationsWithinAFiveKilometersRadius()

    @BeforeEach
    fun setUp() {
        updateGasStations = UpdateGasStations(gasStationsRetriever, gasStationsPersister)
    }

    @Test
    internal fun `download information for gas stations`() {
        every { gasStationsRetriever.apply() }.returns(Result.success(someGasStations))

        updateGasStations.execute()

        verifyOrder {
            gasStationsPersister.replace(someGasStations)
        }
    }

    @Test
    fun `raise an error when something fails while retrieving gas stations`() {
        every { gasStationsRetriever.apply() }.returns(Result.failure(RuntimeException()))

        assertFailsWith<FailedToUpdateGasStation> { updateGasStations.execute() }
    }


}
