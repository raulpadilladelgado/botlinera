package botlinera.infrastructure.adapters

import botlinera.application.ports.GasStationsRetriever
import botlinera.infrastructure.utils.URLWrapper

private const val GAS_STATIONS_SOURCE = "https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/"

class GasStationsRetrieverFromSpanishGovernment (private val url: URLWrapper) : GasStationsRetriever{
    override fun apply(): String {
        return url.get(GAS_STATIONS_SOURCE)
    }
}
