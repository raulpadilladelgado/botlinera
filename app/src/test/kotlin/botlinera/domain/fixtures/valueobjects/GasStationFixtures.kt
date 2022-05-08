package botlinera.domain.fixtures.valueobjects

import botlinera.domain.valueobject.*

class GasStationFixtures {
    companion object {
        fun aGasStation(): GasStation {
            return GasStation(
                "CEPSA",
                Location(
                    "38000",
                    "",
                    "L-S: 06:00-22:00; D: 08:00-21:00",
                    Coordinates("28.481489997320725".toDouble(),
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
    }
}
