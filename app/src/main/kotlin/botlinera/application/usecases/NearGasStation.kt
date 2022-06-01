package botlinera.application.usecases

import botlinera.application.ports.GastStationPersister
import botlinera.domain.valueobject.Coordinates
import botlinera.domain.valueobject.GasStation
import botlinera.domain.valueobject.GasType

class NearGasStation(
    private val gasStationRepository: GastStationPersister
) {
    fun execute(coordinates: Coordinates, maximumDistanceInMeters: Int, gasType: GasType): List<GasStation> {
        return gasStationRepository.queryNearGasStations(
            coordinates.calculateMaximumCoordinates(maximumDistanceInMeters),
            gasType
        )
    }
}
