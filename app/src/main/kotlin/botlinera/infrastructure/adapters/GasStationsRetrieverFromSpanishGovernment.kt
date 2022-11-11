package botlinera.infrastructure.adapters

import botlinera.application.exceptions.FailedToRetrieveGasStations
import botlinera.application.ports.GasStationsRetriever
import botlinera.domain.valueobject.GasStation
import botlinera.infrastructure.dtos.`in`.RetrieverResponseDto
import botlinera.infrastructure.utils.URLWrapper
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException


private const val GAS_STATIONS_SOURCE =
    "https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/"

class GasStationsRetrieverFromSpanishGovernment(private val url: URLWrapper) : GasStationsRetriever {
    override fun apply() = runCatching {
        val gasStationsAsJson = url.get(GAS_STATIONS_SOURCE)
        gasStationsFrom(gasStationsAsJson)
    }.onFailure {
        throw FailedToRetrieveGasStations(it)
    }

    private fun gasStationsFrom(gasStationInfoJson: String): List<GasStation> =
        GsonBuilder()
            .registerTypeAdapter(Double::class.java, DoubleAdapter())
            .create()
            .fromJson(gasStationInfoJson, RetrieverResponseDto::class.java)
            .prices
            .map { it.toDomain() }

    internal class DoubleAdapter : TypeAdapter<Double?>() {
        @Throws(IOException::class)
        override fun write(out: JsonWriter, value: Double?) {
            out.value(value)
        }

        @Throws(IOException::class)
        override fun read(`in`: JsonReader): Double {
            val value = `in`.nextString()
            return if (value.isNotEmpty()) value.replace(",", ".").toDouble() else Double.NaN
        }
    }
}
