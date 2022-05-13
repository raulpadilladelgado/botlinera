package botlinera.domain.fixtures.valueobjects

import botlinera.domain.valueobject.*
import java.lang.Double.NaN

class GasStationFixtures {
    companion object {
        fun aGasStation(): GasStation {
            return GasStation(
                "CEPSA",
                Location(
                    "38000",
                    "",
                    "L-S: 06:00-22:00; D: 08:00-21:00",
                    Coordinates(
                        "28.481489997320725".toDouble(),
                        "-16.313551453654103".toDouble()
                    ),
                    "",
                    ""
                ),
                Prices(
                    Gas95(1.345),
                    Gas98(1.425),
                    Gasoil(1.350),
                )
            )
        }

        fun multipleGasStationsWithinAFiveKilometersRadius(): List<GasStation> {
            return listOf(
                GasStation(
                    "GasStation1",
                    Location(
                        "38660",
                        "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                        "L-D: 08:00-17:30",
                        Coordinates(48.045632, -16.737889),
                        "Adeje", "SANTA CRUZ DE TENERIFE"
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
                        "Adeje", "SANTA CRUZ DE TENERIFE"
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
                        "Adeje", "SANTA CRUZ DE TENERIFE"
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
                        "Adeje", "SANTA CRUZ DE TENERIFE"
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
                        "Adeje", "SANTA CRUZ DE TENERIFE"
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
                        "Adeje", "SANTA CRUZ DE TENERIFE"
                    ),
                    Prices(
                        Gas95(NaN, 1.538, NaN),
                        Gas98(NaN, NaN),
                        Gasoil(1.529, NaN, NaN)
                    )
                )
            )
        }

        fun aGasStationWith(gas95: Gas95, gas98:Gas98, gasoil: Gasoil): GasStation {
           return GasStation(
                "GasStation1",
                Location(
                    "38660",
                    "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                    "L-D: 08:00-17:30",
                    Coordinates(48.045632, -16.737889),
                    "Adeje", "SANTA CRUZ DE TENERIFE"
                ),
                Prices(
                    gas95,
                    gas98,
                    gasoil
                )
            )
        }
    }
}
