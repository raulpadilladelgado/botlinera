package botlinera.application.usecases

import botlinera.domain.valueobject.Coordinates
import botlinera.domain.valueobject.GasStation

class RetrieveNearGasStations {
    fun execute(coordinates: Coordinates): List<GasStation> {
        return listOf()
    }
}
