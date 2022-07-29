package botlinera.application.ports

import botlinera.domain.valueobject.GasStation
import botlinera.domain.valueobject.GasType
import botlinera.domain.valueobject.MaximumCoordinates

interface GasStationPersister {
    fun replace(gasStationDto: List<GasStation>): Result<Unit>
    fun queryNearGasStations(coordinates: MaximumCoordinates, gasType: GasType = GasType.GASOLINA_98_E5): Result<List<GasStation>>
}
