package botlinera.domain.valueobject

import botlinera.domain.fixtures.valueobjects.GasStationFixtures
import org.junit.Test
import java.lang.Double
import kotlin.Double.Companion
import kotlin.Double.Companion.NaN
import kotlin.test.assertEquals

class GasStationShould {

    @Test
    fun formatMessageCorrectlyWhenGas95AndGas98AndGasoilArePresent() {

        val gasStation = GasStationFixtures.aGasStationWith(
            Gas95(1.879, 1.538, 1.465),
            Gas98(1.532, 1.552),
            Gasoil(1.529, 1.598, 1.687)
        )

        val expectedFormattedMessage = """
            ⛽️ GasStation1
            🕐 L-D: 08:00-17:30
            💶 Precio Gasolina
             - 95 E5: 1.538€
             - 95 E10: 1.879€
             - 95 E5 Premium: 1.465€
             - 98 E5: 1.552€
             - 98 E10: 1.532€
            💶 Precio Gasoil
             - A: 1.529€
             - B: 1.598€
             - Premium: 1.687€
        """.trimIndent()

        assertEquals(expectedFormattedMessage, gasStation.formatted())
    }

    @Test
    fun formatMessageShowingOnlyAvailablePrices() {

        val gasStation = GasStationFixtures.aGasStationWith(
            Gas95(1.879, NaN, 1.465),
            Gas98(1.532, NaN),
            Gasoil(1.529, NaN, NaN)
        )

        val expectedFormattedMessage = """
            ⛽️ GasStation1
            🕐 L-D: 08:00-17:30
            💶 Precio Gasolina
             - 95 E10: 1.879€
             - 95 E5 Premium: 1.465€
             - 98 E10: 1.532€
            💶 Precio Gasoil
             - A: 1.529€
        """.trimIndent()

        assertEquals(expectedFormattedMessage, gasStation.formatted())
    }

}
