package botlinera.infrastructure.dtos.`in`

import botlinera.domain.valueobject.*
import com.google.gson.annotations.SerializedName
import java.util.*
import java.util.regex.Pattern

private const val ARTICLES_REGEX = "(.*)\\s?\\((OS|A|OS|A|O|LAS|AS|LA|LES|LOS|S'|EL|L'|ELS|SES|ES|SA)\\)(.*)?|(.*)"

data class GasStationDto(
    @SerializedName("C.P.") val postalCode: String,
    @SerializedName("Dirección") val address: String,
    @SerializedName("Horario") val time: String,
    @SerializedName("Latitud") val latitude: Double,
    @SerializedName("Localidad") val locality: String,
    @SerializedName("Longitud (WGS84)") val longitude: Double,
    @SerializedName("Municipio") val municipality: String,
    @SerializedName("Precio Gasolina 95 E10") val gas95E10Price: Double,
    @SerializedName("Precio Gasolina 95 E5") val gas95E5Price: Double,
    @SerializedName("Precio Gasolina 95 E5 Premium") val gas95E5PremiumPrice: Double,
    @SerializedName("Precio Gasolina 98 E10") val gas98E10Price: Double,
    @SerializedName("Precio Gasolina 98 E5") val gas98E5Price: Double,
    @SerializedName("Provincia") val province: String,
    @SerializedName("Rótulo") val name: String,
    @SerializedName("Precio Gasoleo A") val gasoilA: Double,
    @SerializedName("Precio Gasoleo B") val gasoilB: Double,
    @SerializedName("Precio Gasoleo Premium") val gasoilPremium: Double,
) {
    fun toDomain() = GasStation(
        name,
        Location(
            postalCode,
            address,
            time,
            Coordinates(latitude, longitude),
            municipality,
            province,
            formattedLocality(locality)
        ),
        Prices(
            Gas95(gas95E10Price, gas95E5Price, gas95E5PremiumPrice),
            Gas98(gas98E10Price, gas98E5Price),
            Gasoil(gasoilA, gasoilB, gasoilPremium)
        )
    )


    companion object {
        fun from(gasStation: GasStation) = GasStationDto(
            gasStation.postalCode(),
            gasStation.address(),
            gasStation.time(),
            gasStation.latitude(),
            gasStation.locality(),
            gasStation.longitude(),
            gasStation.municipality(),
            gasStation.prices.gas95.e10,
            gasStation.prices.gas95.e5,
            gasStation.prices.gas95.e5Premium,
            gasStation.prices.gas98.e10,
            gasStation.prices.gas98.e5,
            gasStation.province(),
            gasStation.name,
            gasStation.prices.gasoil.a,
            gasStation.prices.gasoil.b,
            gasStation.prices.gasoil.premium
        )

        internal fun formattedLocality(locality: String): String {
            return capitalize(
                Pattern.compile(ARTICLES_REGEX)
                    .matcher(locality)
                    .replaceAll("$4$2 $1$3")
                    .trim()
                    .lowercase(Locale.getDefault())
                    .removeExtraSpaces()
            )
        }

        private fun capitalize(text: String): String {
            val capitalizedText = StringBuffer()
            val matcher = Pattern.compile("\\b(\\w)").matcher(text)
            while (matcher.find()) matcher.appendReplacement(capitalizedText, matcher.group(1).uppercase(Locale.getDefault()))
            matcher.appendTail(capitalizedText)
            return capitalizedText.toString()
        }

        private fun CharSequence.removeExtraSpaces(): String {
            return replace("\\s+".toRegex(), " ")
        }
    }
}