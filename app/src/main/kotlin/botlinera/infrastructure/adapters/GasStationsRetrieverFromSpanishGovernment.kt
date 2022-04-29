package botlinera.infrastructure.adapters

import botlinera.application.ports.GasStationsRetriever
import botlinera.infrastructure.dtos.GasStationDto
import botlinera.infrastructure.dtos.RetrieverResponseDto
import botlinera.infrastructure.utils.URLWrapper
import com.google.gson.Gson

private const val GAS_STATIONS_SOURCE = "https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/"

class GasStationsRetrieverFromSpanishGovernment (private val url: URLWrapper) : GasStationsRetriever{
    override fun apply(): List<GasStationDto> {
        val gasStationInfoJson = url.get(GAS_STATIONS_SOURCE)
        return Gson().fromJson(gasStationInfoJson, RetrieverResponseDto::class.java).prices
    }
}
