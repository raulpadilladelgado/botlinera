package botlinera.application.usecases

import botlinera.application.ports.GasStationsRetriever
import botlinera.application.ports.GastStationPersister
import botlinera.infrastructure.dtos.GasStation
import org.mockito.Mockito.*
import java.io.File
import kotlin.test.Test


private const val EXPECTED_GAS_STATION_JSON = "/real-example-of-gas-stations-from-spanish-government.json"

class RetrieveGasStationsShould {
    @Test fun getInfoForAllGasStations () {
        val fakeGasStationsRetriever = mock(GasStationsRetriever::class.java)
        val fakeGasStationsPersister = mock(GastStationPersister::class.java)
        `when`(fakeGasStationsRetriever.apply()).thenReturn(gasStationExpected())
        val retrieveGasStations = RetrieveGasStations(fakeGasStationsRetriever, fakeGasStationsPersister)
        retrieveGasStations.execute()
        verify (fakeGasStationsPersister, times(1)).save(gasStationExpected())
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
