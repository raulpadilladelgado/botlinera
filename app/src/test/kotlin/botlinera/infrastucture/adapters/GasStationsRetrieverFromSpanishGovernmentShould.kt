package botlinera.infrastucture.adapters

import botlinera.infrastructure.adapters.GasStationsRetrieverFromSpanishGovernment
import botlinera.infrastructure.utils.URLWrapper
import org.junit.Test
import org.mockito.Mockito.*


private const val GAS_STATIONS_SOURCE = "https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/"

class GasStationsRetrieverFromSpanishGovernmentShould {
    //TODO define a "real" expected result
    @Test
    fun getInfoForAllGasStations () {
        val requester: URLWrapper = mock(URLWrapper::class.java)
        val gasStationsRetrieverFromSpanishGovernment = GasStationsRetrieverFromSpanishGovernment(requester)
        gasStationsRetrieverFromSpanishGovernment.apply()
        verify(requester, times(1)).get(GAS_STATIONS_SOURCE)
    }
}
