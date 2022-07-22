package botlinera.infrastucture.adapters

import botlinera.application.exceptions.FailedToRetrieveGasStations
import botlinera.domain.fixtures.valueobjects.GasStationFixtures.Companion.aGasStation
import botlinera.infrastructure.adapters.GasStationsRetrieverFromSpanishGovernment
import botlinera.infrastructure.utils.URLWrapper
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertFailsWith


private const val EXPECTED_GAS_STATION_JSON = "/real-example-of-gas-stations-from-spanish-government.json"

@ExtendWith(MockKExtension::class)
class GasStationsRetrieverFromSpanishGovernmentShould {
    @MockK
    private lateinit var requester: URLWrapper

    private lateinit var gasStationsRetriever: GasStationsRetrieverFromSpanishGovernment

    @BeforeEach
    internal fun setUp() {
        gasStationsRetriever = GasStationsRetrieverFromSpanishGovernment(requester)
    }

    @Test
    fun `download gas stations info`() {
        val expectedGasStation = javaClass.getResource(EXPECTED_GAS_STATION_JSON)?.readText()!!
        every { requester.get(any()) }.returns(expectedGasStation)
        val gasStationsInfo = gasStationsRetriever.apply()
        assertEquals(aGasStation(), gasStationsInfo.getOrThrow())
    }

    @Test
    fun `raise an error when malformed response`() {
        every { requester.get(any()) }.returns("")

        assertFailsWith<FailedToRetrieveGasStations> {
            gasStationsRetriever.apply()
        }
    }

    @Test
    fun `raise an error when failing to retrieve gas stations`() {
        every { requester.get(any()) }.throws(RuntimeException())

        assertFailsWith<FailedToRetrieveGasStations> { gasStationsRetriever.apply() }
    }
}
