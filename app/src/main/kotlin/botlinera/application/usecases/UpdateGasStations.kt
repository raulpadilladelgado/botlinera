package botlinera.application.usecases

import botlinera.application.ports.GasStationsRetriever
import botlinera.application.ports.GastStationPersister

class UpdateGasStations(
    private val gasStationsRetriever: GasStationsRetriever,
    private val gasStationPersister: GastStationPersister,
) {
    fun execute() = runCatching {
        val gasStations = gasStationsRetriever.apply().getOrThrow()
        gasStationPersister.replace(gasStations).getOrThrow()
    }.onFailure {
        throw it
    }
}
