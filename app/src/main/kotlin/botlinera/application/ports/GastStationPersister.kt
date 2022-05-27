package botlinera.application.ports

import botlinera.domain.valueobject.GasStation
import botlinera.domain.valueobject.GasType
import botlinera.domain.valueobject.MaximumCoordinates
import botlinera.infrastructure.dtos.GasStationDto

interface GastStationPersister {
    fun save(gasStationDto: List<GasStationDto>)
    fun delete()
    fun queryNearGasStations(coordinates: MaximumCoordinates, gasolina95E5: GasType): List<GasStation>
}
