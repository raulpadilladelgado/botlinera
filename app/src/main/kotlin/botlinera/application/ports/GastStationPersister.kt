package botlinera.application.ports

import botlinera.infrastructure.dtos.GasStationDto

interface GastStationPersister {
    fun save(gasStationsInfoDto: List<GasStationDto>)
    fun delete()
}
