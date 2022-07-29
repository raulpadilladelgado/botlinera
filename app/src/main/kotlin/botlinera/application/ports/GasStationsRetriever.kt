package botlinera.application.ports

import botlinera.domain.valueobject.GasStation
import botlinera.infrastructure.dtos.GasStationDto

interface GasStationsRetriever {
    fun apply(): Result<List<GasStation>>
}
