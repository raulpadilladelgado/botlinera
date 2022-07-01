package botlinera.application.usecases

import botlinera.application.exceptions.FailedToRetrieveGasStations
import botlinera.application.ports.GasStationsRetriever
import botlinera.application.ports.GastStationPersister

class UpdateGasStations(
    private val gasStationsRetriever: GasStationsRetriever,
    private val gasStationPersister: GastStationPersister,
) {
    fun execute() {
        gasStationsRetriever.apply()
            .onSuccess {
                gasStationPersister.delete()
                gasStationPersister.save(it)
            }
            .onFailure {
                throw FailedToRetrieveGasStations(it)
            }
    }
}
