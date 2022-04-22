package botlinera.application.ports

import botlinera.infrastructure.dtos.GasStation

interface GasStationsRetriever {
    fun apply(): List<GasStation>
}
