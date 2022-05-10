package botlinera.application.usecases

import botlinera.application.ports.GastStationPersister
import botlinera.domain.valueobject.Coordinates
import botlinera.domain.valueobject.GasStation

class NearGasStation (
    val gasStationRepository: GastStationPersister
) {
    fun execute(coordinates: Coordinates): List<GasStation> {
        return gasStationRepository.queryNearGasStations(coordinates.calculateMaximumCoordinates())
    }
}
