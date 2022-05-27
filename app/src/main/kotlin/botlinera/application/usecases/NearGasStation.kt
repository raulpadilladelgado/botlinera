package botlinera.application.usecases

import botlinera.application.ports.GastStationPersister
import botlinera.domain.valueobject.Coordinates
import botlinera.domain.valueobject.GasStation
import botlinera.domain.valueobject.GasType

class NearGasStation(
    val gasStationRepository: GastStationPersister
) {
    fun execute(coordinates: Coordinates, gasolina98E5: GasType): List<GasStation> {
        return gasStationRepository.queryNearGasStations(
            coordinates.calculateMaximumCoordinates(),
            GasType.GASOLINA_95_E5
        )
    }
}
