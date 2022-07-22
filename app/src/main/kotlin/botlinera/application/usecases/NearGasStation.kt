package botlinera.application.usecases

import botlinera.application.exceptions.FailedToRetrieveNearGasStations
import botlinera.application.ports.GasStationPersister
import botlinera.domain.valueobject.Coordinates
import botlinera.domain.valueobject.GasStation
import botlinera.domain.valueobject.GasType

class NearGasStation(
    private val gasStationRepository: GasStationPersister
) {
    fun execute(coordinates: Coordinates, maximumDistanceInMeters: Int, gasType: GasType): List<GasStation> {
        return gasStationRepository.queryNearGasStations(
            coordinates.calculateMaximumCoordinates(maximumDistanceInMeters),
            gasType
        ).onFailure { error ->
            throw FailedToRetrieveNearGasStations(error)
        }.getOrThrow()
    }
}
