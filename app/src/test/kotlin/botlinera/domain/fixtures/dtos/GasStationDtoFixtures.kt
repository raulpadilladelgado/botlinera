package botlinera.domain.fixtures.dtos

import botlinera.infrastructure.dtos.GasStationDto

class GasStationDtoFixtures {
    companion object {
        fun gasStation(): List<GasStationDto> = listOf(
            GasStationDto(
                "02250",
                "AVENIDA CASTILLA LA MANCHA, 26",
                "L-D: 07:00-22:00",
                "39,211417",
                "ABENGIBRE",
                "-1,539167",
                "Abengibre",
                "",
                "1,759",
                "",
                "",
                "",
                "ALBACETE",
            "CEPSA"
            )
        )
    }
}
