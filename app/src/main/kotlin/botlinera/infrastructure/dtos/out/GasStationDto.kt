package botlinera.infrastructure.dtos.out

import botlinera.domain.valueobject.*
import java.time.LocalDateTime
import java.time.ZoneId

data class GasStationDto(
    val postalCode: String,
    val address: String,
    val time: String,
    val latitude: Double,
    val locality: String,
    val longitude: Double,
    val municipality: String,
    val gas95E10Price: Double,
    val gas95E5Price: Double,
    val gas95E5PremiumPrice: Double,
    val gas98E10Price: Double,
    val gas98E5Price: Double,
    val province: String,
    val name: String,
    val gasoilA: Double,
    val gasoilB: Double,
    val gasoilPremium: Double,
    val updatedAt: LocalDateTime
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
            locality
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
            gasStation.prices.gasoil.premium,
            getCurrentTimeByPostalCode(gasStation.postalCode())
        )

        private fun getCurrentTimeByPostalCode(postalCode: String): LocalDateTime {
            return if (postalCode.startsWith("38") || postalCode.startsWith("35")) {
                LocalDateTime.now(ZoneId.of("Atlantic/Canary"));
            } else {
                LocalDateTime.now(ZoneId.of("Europe/Madrid"));
            }
        }
    }
}