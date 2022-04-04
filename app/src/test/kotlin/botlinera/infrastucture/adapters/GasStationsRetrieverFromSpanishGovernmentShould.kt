package botlinera.infrastucture.adapters

import botlinera.infrastructure.adapters.GasStationsRetrieverFromSpanishGovernment
import botlinera.infrastructure.utils.URLWrapper
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import kotlin.test.assertEquals


class GasStationsRetrieverFromSpanishGovernmentShould {
    //TODO define a "real" expected result
    @Test
    fun getInfoForAllGasStations () {
        val requester: URLWrapper = mock(URLWrapper::class.java)
        `when`(requester.get("some official source")).thenReturn("")
        val gasStationsRetrieverFromSpanishGovernment = GasStationsRetrieverFromSpanishGovernment(requester)
        val result = gasStationsRetrieverFromSpanishGovernment.apply()
        assertEquals("", result)
    }
}
