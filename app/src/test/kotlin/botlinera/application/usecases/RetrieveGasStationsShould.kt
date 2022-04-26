package botlinera.application.usecases

import botlinera.application.ports.GasStationsRetriever
import botlinera.application.ports.GastStationPersister
import botlinera.domain.fixtures.GasStationFixtures.Companion.gasStation
import org.mockito.Mockito.*
import kotlin.test.Test

class RetrieveGasStationsShould {
    @Test
    fun getInfoForAllGasStations() {
        val fakeGasStationsRetriever = mock(GasStationsRetriever::class.java)
        val fakeGasStationsPersister = mock(GastStationPersister::class.java)
        `when`(fakeGasStationsRetriever.apply()).thenReturn(gasStation())
        val retrieveGasStations = RetrieveGasStations(fakeGasStationsRetriever, fakeGasStationsPersister)
        retrieveGasStations.execute()
        verify(fakeGasStationsPersister, times(1)).save(gasStation())
    }
}
