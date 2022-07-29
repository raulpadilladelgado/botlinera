package botlinera.domain.fixtures.valueobjects

import botlinera.domain.valueobject.*
import kotlin.Double.Companion.NaN

class GasStationFixtures {
    companion object {
        fun multipleGasStationsWithinAFiveKilometersRadius(): List<GasStation> {
            return listOf(
                GasStation(
                    "GasStation1",
                    Location(
                        "38660",
                        "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                        "L-D: 08:00-17:30",
                        Coordinates(48.045632, -16.737889),
                        "Adeje", "SANTA CRUZ DE TENERIFE", "Adeje"
                    ),
                    Prices(
                        Gas95(NaN, 1.538, NaN),
                        Gas98(NaN, NaN),
                        Gasoil(1.529, NaN, NaN)
                    )
                ),
                GasStation(
                    "GasStation2",
                    Location(
                        "38661",
                        "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                        "L-D: 08:00-17:30",
                        Coordinates(28.069, -20.7845454),
                        "Adeje", "SANTA CRUZ DE TENERIFE", "Adeje"
                    ),
                    Prices(
                        Gas95(NaN, 1.538, NaN),
                        Gas98(NaN, NaN),
                        Gasoil(1.529, NaN, NaN)
                    )
                ),
                GasStation(
                    "GasStation3",
                    Location(
                        "38662",
                        "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                        "L-D: 08:00-17:30",
                        Coordinates(28.011861, -16.662639),
                        "Adeje", "SANTA CRUZ DE TENERIFE", "Adeje"
                    ),
                    Prices(
                        Gas95(NaN, 1.538, NaN),
                        Gas98(NaN, NaN),
                        Gasoil(1.529, NaN, NaN)
                    )
                ),
                GasStation(
                    "GasStation4",
                    Location(
                        "38663",
                        "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                        "L-D: 08:00-17:30",
                        Coordinates(28.053583, -16.714611),
                        "Adeje", "SANTA CRUZ DE TENERIFE", "Adeje"
                    ),
                    Prices(
                        Gas95(NaN, 1.538, NaN),
                        Gas98(NaN, NaN),
                        Gasoil(1.529, NaN, NaN)
                    )
                ),
                GasStation(
                    "GasStation5",
                    Location(
                        "38663",
                        "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                        "L-D: 08:00-17:30",
                        Coordinates(5.053583, -16.714611),
                        "Adeje", "SANTA CRUZ DE TENERIFE", "Adeje"
                    ),
                    Prices(
                        Gas95(NaN, 1.538, NaN),
                        Gas98(NaN, NaN),
                        Gasoil(1.529, NaN, NaN)
                    )
                ),
                GasStation(
                    "GasStation6",
                    Location(
                        "38663",
                        "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                        "L-D: 08:00-17:30",
                        Coordinates(28.069, -1.714611),
                        "Adeje", "SANTA CRUZ DE TENERIFE", "Adeje"
                    ),
                    Prices(
                        Gas95(NaN, 1.538, NaN),
                        Gas98(NaN, NaN),
                        Gasoil(1.529, NaN, NaN)
                    )
                )
            )
        }

        fun aGasStationWith(gas95: Gas95, gas98: Gas98, gasoil: Gasoil): GasStation {
            return GasStation(
                "GasStation1",
                Location(
                    "38660",
                    "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                    "L-D: 08:00-17:30",
                    Coordinates(48.045632, -16.737889),
                    "Adeje", "SANTA CRUZ DE TENERIFE", "Adeje"
                ),
                Prices(
                    gas95,
                    gas98,
                    gasoil
                )
            )
        }

        fun aGasStation(): List<GasStation> = listOf(
            GasStation(
                "CEPSA",
                Location(
                    "02250", "AVENIDA CASTILLA LA MANCHA, 26", "L-D: 07:00-22:00",
                    Coordinates(39.211417,-1.539167 ),
                    "Abengibre",
                    "ALBACETE",
                    "ABENGIBRE",
                ),
                Prices(Gas95(NaN, 1.759, NaN), Gas98(NaN, NaN), Gasoil(1.779, 1.270, NaN))
            )
        )
    }
}
