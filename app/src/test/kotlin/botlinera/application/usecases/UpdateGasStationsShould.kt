package botlinera.application.usecases

import botlinera.application.ports.GasStationsRetriever
import botlinera.application.ports.GastStationPersister
import botlinera.domain.fixtures.dtos.GasStationDtoFixtures.Companion.gasStation
import org.mockito.Mockito.*
import kotlin.test.Test

class UpdateGasStationsShould {
    @Test
    fun getInfoForAllGasStations() {
        val fakeGasStationsRetriever = mock(GasStationsRetriever::class.java)
        val fakeGasStationsPersister = mock(GastStationPersister::class.java)
        `when`(fakeGasStationsRetriever.apply()).thenReturn(gasStation())
        val retrieveGasStations = UpdateGasStations(fakeGasStationsRetriever, fakeGasStationsPersister)
        retrieveGasStations.execute()
        val order = inOrder(fakeGasStationsPersister)
        order.verify(fakeGasStationsPersister, times(1)).delete()
        order.verify(fakeGasStationsPersister, times(1)).save(gasStation())
    }
}
