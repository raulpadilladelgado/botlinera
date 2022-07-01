package botlinera.application.ports

import botlinera.infrastructure.dtos.GasStationDto

interface GasStationsRetriever {
    fun apply(): Result<List<GasStationDto>>
}
