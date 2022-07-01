package botlinera.application.ports

import botlinera.domain.valueobject.GasStation
import botlinera.domain.valueobject.GasType
import botlinera.domain.valueobject.MaximumCoordinates
import botlinera.infrastructure.dtos.GasStationDto

interface GastStationPersister {
    fun replace(gasStationDto: List<GasStationDto>): Result<Unit>
    fun queryNearGasStations(coordinates: MaximumCoordinates, gasType: GasType = GasType.GASOLINA_98_E5): List<GasStation>
}
