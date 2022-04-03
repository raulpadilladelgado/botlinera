package botlinera.application.usecases

import botlinera.application.ports.GasStationsRetriever

class RetrieveGasStations(private val gasStationsRetriever: GasStationsRetriever) {
    fun execute(): String {
        return gasStationsRetriever.apply()
    }
}
