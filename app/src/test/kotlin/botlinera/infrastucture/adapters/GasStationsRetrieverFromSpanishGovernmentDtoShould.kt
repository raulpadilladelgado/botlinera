package botlinera.infrastucture.adapters

import botlinera.domain.fixtures.dtos.GasStationDtoFixtures.Companion.someGasStationsDto
import botlinera.infrastructure.adapters.GasStationsRetrieverFromSpanishGovernment
import botlinera.infrastructure.utils.URLWrapper
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


private const val EXPECTED_GAS_STATION_JSON = "/real-example-of-gas-stations-from-spanish-government.json"

@ExtendWith(MockKExtension::class)
class GasStationsRetrieverFromSpanishGovernmentDtoShould {
    @MockK
    private lateinit var requester: URLWrapper

    @Test
    fun deserializeToGasStationInfo() {
        val file = javaClass.getResource(EXPECTED_GAS_STATION_JSON)?.readText()!!
        every { requester.get(any<String>()) }.returns(file)
        val gasStationsRetrieverFromSpanishGovernment = GasStationsRetrieverFromSpanishGovernment(requester)
        val gasStationsInfo = gasStationsRetrieverFromSpanishGovernment.apply()
        assertEquals(someGasStationsDto(), gasStationsInfo.getOrThrow())
    }
}
