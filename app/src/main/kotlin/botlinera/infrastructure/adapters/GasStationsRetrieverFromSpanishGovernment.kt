package botlinera.infrastructure.adapters

import botlinera.application.ports.GasStationsRetriever
import botlinera.infrastructure.dtos.GasStation
import botlinera.infrastructure.dtos.RetrieverResponse
import botlinera.infrastructure.utils.URLWrapper
import com.google.gson.Gson

private const val GAS_STATIONS_SOURCE = "https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/"

class GasStationsRetrieverFromSpanishGovernment (private val url: URLWrapper) : GasStationsRetriever{
    override fun apply(): List<GasStation> {
        val gasStationInfoJson = url.get(GAS_STATIONS_SOURCE)
        return Gson().fromJson(gasStationInfoJson, RetrieverResponse::class.java).prices
    }
}
