package botlinera.application.usecases

import botlinera.application.ports.GasStationsRetriever
import botlinera.application.ports.GastStationPersister
import org.mockito.Mockito.*
import kotlin.test.Test


private const val EXPECTED_GAS_STATION_JSON = "gas station json"

class RetrieveGasStationsShould {
    @Test fun getInfoForAllGasStations () {
        val fakeGasStationsRetriever = mock(GasStationsRetriever::class.java)
        val fakeGasStationsPersister = mock(GastStationPersister::class.java)
        `when`(fakeGasStationsRetriever.apply()).thenReturn(EXPECTED_GAS_STATION_JSON)
        val retrieveGasStations = RetrieveGasStations(fakeGasStationsRetriever, fakeGasStationsPersister)
        retrieveGasStations.execute()
        verify (fakeGasStationsPersister, times(1)).save(EXPECTED_GAS_STATION_JSON)
    }
}
