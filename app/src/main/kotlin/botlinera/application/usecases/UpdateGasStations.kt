package botlinera.application.usecases

import botlinera.application.ports.GasStationsRetriever
import botlinera.application.ports.GastStationPersister

class UpdateGasStations(
    private val gasStationsRetriever: GasStationsRetriever,
    private val gasStationPersister: GastStationPersister,
) {
    fun execute() {
        val gasStationsInfo = gasStationsRetriever.apply()
        gasStationPersister.delete()
        gasStationPersister.save(gasStationsInfo)
    }
}