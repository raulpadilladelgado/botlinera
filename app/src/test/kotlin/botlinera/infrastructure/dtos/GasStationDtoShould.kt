package botlinera.infrastructure.dtos

import botlinera.infrastructure.dtos.`in`.GasStationDto
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class GasStationDtoShould {
    @ParameterizedTest
    @CsvSource(
        value = [
            "LOCALIDAD,Localidad",
            "LOCALIDAD (OS),Os Localidad",
            "LOCALIDAD (OS) (PEPE JUAN),Os Localidad (Pepe Juan)",
            "LOCALIDAD (A),A Localidad",
            "LOCALIDAD (O),O Localidad",
            "LOCALIDAD (LAS),Las Localidad",
            "LOCALIDAD (AS),As Localidad",
            "LOCALIDAD (LA),La Localidad",
            "LOCALIDAD (LES),Les Localidad",
            "LOCALIDAD (LOS),Los Localidad",
            "LOCALIDAD (S'),S' Localidad",
            "LOCALIDAD (EL),El Localidad",
            "LOCALIDAD (L'),L' Localidad",
            "LOCALIDAD (ELS),Els Localidad",
            "LOCALIDAD (SES),Ses Localidad",
            "LOCALIDAD (ES),Es Localidad",
            "LOCALIDAD (SA),Sa Localidad",
            "LOCALIDAD(LA),La Localidad"
        ]
    )
    fun `format locality to be more humanly legible`(locality: String, expectedFormattedLocality: String) {
        val formattedLocality = GasStationDto.formattedLocality(locality)

        assertEquals(expectedFormattedLocality, formattedLocality)
    }
}