package botlinera.application.usecases

import botlinera.application.ports.GasStationsRetriever
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test



class RetrieveGasStationsShould {
    @Test fun getInfoForAllGasStations () {
        val fakeGasStationsRetriever = mockk<GasStationsRetriever>()
        every { fakeGasStationsRetriever.apply() } returns ""
        val retrieveGasStations = RetrieveGasStations(fakeGasStationsRetriever)
        retrieveGasStations.execute()
        verify { fakeGasStationsRetriever.apply() }
    }
}
