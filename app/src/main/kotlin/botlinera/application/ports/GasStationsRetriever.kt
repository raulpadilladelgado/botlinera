package botlinera.application.ports

import botlinera.domain.valueobject.GasStation
import botlinera.infrastructure.dtos.`in`.GasStationDto

interface GasStationsRetriever {
    fun apply(): Result<List<GasStation>>
}
