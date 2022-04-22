package botlinera.application.usecases

import botlinera.application.ports.GasStationsRetriever
import botlinera.application.ports.GastStationPersister
import org.mockito.Mockito.*
import java.io.File
import kotlin.test.Test


private const val EXPECTED_GAS_STATION_JSON = "real-example-of-gas-stations-from-spanish-government.json"

class RetrieveGasStationsShould {
//    @Test fun getInfoForAllGasStations () {
//        val file = javaClass.getResource(EXPECTED_GAS_STATION_JSON)?.readText()
//        val fakeGasStationsRetriever = mock(GasStationsRetriever::class.java)
//        val fakeGasStationsPersister = mock(GastStationPersister::class.java)
//        `when`(fakeGasStationsRetriever.apply()).thenReturn(file)
//        val retrieveGasStations = RetrieveGasStations(fakeGasStationsRetriever, fakeGasStationsPersister)
//        retrieveGasStations.execute()
//        verify (fakeGasStationsPersister, times(1)).save(EXPECTED_GAS_STATION_JSON)
//    }
}
