package botlinera.application.ports

import botlinera.domain.valueobject.Coordinates
import botlinera.domain.valueobject.GasStation
import botlinera.infrastructure.dtos.GasStationDto

interface GastStationPersister {
    fun save(gasStationsInfoDto: List<GasStationDto>)
    fun delete()
    fun queryNearGasStations(coordinates: Coordinates): List<GasStation>
}
