package botlinera.application.usecases

import botlinera.application.ports.GasStationsRetriever
import org.mockito.Mockito.*
import kotlin.test.Test


class RetrieveGasStationsShould {
    @Test fun getInfoForAllGasStations () {
        val fakeGasStationsRetriever = mock(GasStationsRetriever::class.java)
        val retrieveGasStations = RetrieveGasStations(fakeGasStationsRetriever)
        retrieveGasStations.execute()
        verify (fakeGasStationsRetriever, times(1)).apply()
    }
}
