package botlinera.application.usecases

import botlinera.application.ports.GastStationPersister
import botlinera.domain.valueobject.Coordinates
import botlinera.domain.valueobject.GasStation

class RetrieveNearGasStations (
    private val gasStationPersister: GastStationPersister
){
    fun execute(coordinates: Coordinates): List<GasStation> {
        return gasStationPersister.queryNearGasStations(coordinates)
    }
}
