package botlinera.infrastructure.adapters

import botlinera.application.ports.GasStationsRetriever
import botlinera.infrastructure.dtos.GasStationDto
import botlinera.infrastructure.dtos.RetrieverResponseDto
import botlinera.infrastructure.utils.URLWrapper
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException


private const val GAS_STATIONS_SOURCE =
    "https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/"

class GasStationsRetrieverFromSpanishGovernment(private val url: URLWrapper) : GasStationsRetriever {
    override fun apply(): Result<List<GasStationDto>> {
        val builder = GsonBuilder()
        builder.registerTypeAdapter(Double::class.java, DoubleAdapter())
        val gson = builder.create()
        val gasStationInfoJson = url.get(GAS_STATIONS_SOURCE)
        return Result.success(gson
            .fromJson(gasStationInfoJson, RetrieverResponseDto::class.java)
            .prices)
    }

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
