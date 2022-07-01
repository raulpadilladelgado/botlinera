package botlinera.application.usecases

import botlinera.application.exceptions.FailedToReplaceGasStations
import botlinera.application.exceptions.FailedToRetrieveGasStations
import botlinera.application.ports.GasStationsRetriever
import botlinera.application.ports.GastStationPersister
import botlinera.domain.fixtures.dtos.GasStationDtoFixtures.Companion.someGasStationsDto
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
    private lateinit var gasStationsPersister: GastStationPersister
    private lateinit var updateGasStations: UpdateGasStations
    private val someGasStationsDto = someGasStationsDto()

    @BeforeEach
    fun setUp() {
        updateGasStations = UpdateGasStations(gasStationsRetriever, gasStationsPersister)
    }

    @Test
    internal fun `download information for gas stations`() {
        every { gasStationsRetriever.apply() }.returns(Result.success(someGasStationsDto))

        updateGasStations.execute()

        verifyOrder {
            gasStationsPersister.replace(someGasStationsDto)
        }
    }

    @Test
    fun `raise an error when something fails while download gas stations`() {
        every { gasStationsRetriever.apply() }.returns(Result.failure(FailedToRetrieveGasStations(RuntimeException())))

        assertFailsWith<FailedToRetrieveGasStations> { updateGasStations.execute() }
    }

    @Test
    fun `raise an error when something fails while replacing gas stations in repository`() {
        every { gasStationsRetriever.apply() }.returns(Result.success(someGasStationsDto))
        every { gasStationsPersister.replace(someGasStationsDto) }.returns(Result.failure(FailedToReplaceGasStations(RuntimeException())))

        assertFailsWith<FailedToReplaceGasStations> { updateGasStations.execute() }
    }
}
