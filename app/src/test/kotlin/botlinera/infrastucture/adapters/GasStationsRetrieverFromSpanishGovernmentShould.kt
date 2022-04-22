package botlinera.infrastucture.adapters

import botlinera.infrastructure.adapters.GasStationsRetrieverFromSpanishGovernment
import botlinera.infrastructure.dtos.GasStation
import botlinera.infrastructure.utils.URLWrapper
import org.junit.Test
import org.mockito.Mockito.*
import kotlin.test.assertEquals


private const val EXPECTED_GAS_STATION_JSON = "/real-example-of-gas-stations-from-spanish-government.json"

class GasStationsRetrieverFromSpanishGovernmentShould {
    @Test
    fun deserializeToGasStationInfo() {
        val requester: URLWrapper = mock(URLWrapper::class.java)
        val file = javaClass.getResource(EXPECTED_GAS_STATION_JSON)?.readText()
        `when`(requester.get(anyString())).thenReturn(file)

        val gasStationsRetrieverFromSpanishGovernment = GasStationsRetrieverFromSpanishGovernment(requester)
        var gasStationsInfo = gasStationsRetrieverFromSpanishGovernment.apply()

        assertEquals(gasStationExpected(), gasStationsInfo)
    }

    private fun gasStationExpected() = listOf(
        GasStation(
            "02250",
            "AVENIDA CASTILLA LA MANCHA, 26",
            "L-D: 07:00-22:00",
            "39,211417",
            "ABENGIBRE",
            "-1,539167",
            "Abengibre",
            "",
            "1,759",
            "",
            "",
            "",
            "ALBACETE"
        )
    )
}
