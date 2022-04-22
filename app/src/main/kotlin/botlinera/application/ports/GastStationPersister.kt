package botlinera.application.ports

import botlinera.infrastructure.dtos.GasStation

interface GastStationPersister {
    fun save(gasStationsInfo: ArrayList<GasStation>)
}
