package botlinera.domain.fixtures.dtos

import botlinera.infrastructure.dtos.GasStationDto

class GasStationDtoFixtures {
    companion object {
        fun gasStation(): List<GasStationDto> = listOf(
            GasStationDto(
                "02250",
                "AVENIDA CASTILLA LA MANCHA, 26",
                "L-D: 07:00-22:00",
                "39.211417".toDouble(),
                "ABENGIBRE",
                "-1.539167".toDouble(),
                "Abengibre",
                Double.NaN,
                "1.759".toDouble(),
                Double.NaN,
                Double.NaN,
                Double.NaN,
                "ALBACETE",
                "CEPSA",
                "1.779".toDouble(),
                "1.270".toDouble(),
                Double.NaN,
            )
        )
    }
}
