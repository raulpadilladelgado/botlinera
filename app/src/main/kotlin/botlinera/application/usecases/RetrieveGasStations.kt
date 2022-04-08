package botlinera.application.usecases

import botlinera.application.ports.GasStationsRetriever
import botlinera.application.ports.GastStationPersister

class RetrieveGasStations(
    private val gasStationsRetriever: GasStationsRetriever,
    private val gasStationPersister: GastStationPersister
) {
    fun execute() {
        val gasStationsInfo = gasStationsRetriever.apply()
        gasStationPersister.save(gasStationsInfo)
    }
}
