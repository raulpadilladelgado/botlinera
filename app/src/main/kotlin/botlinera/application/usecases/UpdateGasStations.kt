package botlinera.application.usecases

import botlinera.application.exceptions.FailedToUpdateGasStation
import botlinera.application.ports.GasStationPersister
import botlinera.application.ports.GasStationsRetriever
import botlinera.domain.valueobject.GasStation
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class UpdateGasStations(
    private val gasStationsRetriever: GasStationsRetriever,
    private val gasStationPersister: GasStationPersister,
) {
    companion object {
        val LOG: Logger = LoggerFactory.getLogger(UpdateGasStations::class.java)
    }

    fun execute() = runCatching {
        val gasStations: List<GasStation> = gasStationsRetriever.apply().getOrThrow()
        gasStationPersister.replace(gasStations).getOrThrow()
        LOG.info("Gas stations were updated in database")
    }.onFailure {
        LOG.error("Error occurred while updating gas stations in database", it)
        throw FailedToUpdateGasStation(it)
    }
}
