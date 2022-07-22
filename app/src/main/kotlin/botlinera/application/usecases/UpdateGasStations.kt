package botlinera.application.usecases

import botlinera.application.exceptions.FailedToUpdateGasStation
import botlinera.application.ports.GasStationsRetriever
import botlinera.application.ports.GasStationPersister
import botlinera.domain.valueobject.GasStation

class UpdateGasStations(
    private val gasStationsRetriever: GasStationsRetriever,
    private val gasStationPersister: GasStationPersister,
) {
    fun execute() = runCatching {
        val gasStations: List<GasStation> = gasStationsRetriever.apply().getOrThrow()
        gasStationPersister.replace(gasStations).getOrThrow()
    }.onFailure {
        throw FailedToUpdateGasStation(it)
    }
}
