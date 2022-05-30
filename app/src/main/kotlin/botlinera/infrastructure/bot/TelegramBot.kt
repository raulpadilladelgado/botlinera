package botlinera.infrastructure.bot

import botlinera.application.usecases.NearGasStation
import botlinera.domain.valueobject.Coordinates
import botlinera.domain.valueobject.GasStation
import botlinera.domain.valueobject.GasType
import botlinera.domain.valueobject.GasType.*
import botlinera.infrastructure.adapters.GastStationPersisterMongo
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.location
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId.Companion.fromId
import com.github.kotlintelegrambot.entities.ParseMode.MARKDOWN
import java.lang.System.getenv

class TelegramBot {
    fun startPolling() {
        initBot()
    }

    private fun initBot() = bot {
        token = getenv("TELEGRAM_BOT_TOKEN")
        var gasType = GASOLINA_95_E5
        dispatch {
            text {
                if (text !in commands) {
                    bot.sendMessage(
                        fromId(message.chat.id),
                        text = """
                        ¡Hola! Me llamo *Botlinera* 🤖⛽️ y estoy aquí para ayudarte a ahorrar dinero 💸.
                        
                        Para empezar solo tienes que enviarme tu ubicación para así mostrarte las *tres gasolineras más baratas* cerca de ti. 
                        
                        Recuerda que a la izquierda del cuadro para introducir texto se encuentra un menú en el que podrás elegir que tipo de combustible sueles echar y te mostraré los resultado ordenados en base a ese criterio
                        """.trimIndent(),
                        MARKDOWN
                    )
                }
            }
            location {
                val nearGasStations = getNearGasStations(location.latitude, location.longitude, gasType)
                if (nearGasStations.isEmpty()) {
                    bot.sendMessage(
                        fromId(message.chat.id), text = """
                            Lo siento, no he podido encontrar gasolineras que vendan $gasType
                            
                            Intentalo en otro momento o contacta con los que me programaron
                        """.trimIndent()
                    )
                }
                nearGasStations
                    .forEach { gasStation ->
                        bot.sendMessage(
                            fromId(message.chat.id), text = gasStation.formatted()
                        )
                        bot.sendLocation(
                            fromId(message.chat.id),
                            gasStation.latitude().toFloat(),
                            gasStation.longitude().toFloat()
                        )
                    }
            }
            command("gasolina95e5") {
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = """
                        Quieres echar gasolina 95 E5. Ahora manda tu ubicación y te buscaré las tres más baratas cerca de ti
                        """.trimIndent(),
                    MARKDOWN
                )
                gasType = GASOLINA_95_E5
            }
            command("gasolina95e5premium") {
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = """
                        Quieres echar gasolina 95 E5 Premium. Ahora manda tu ubicación y te buscaré las tres más baratas cerca de ti
                        """.trimIndent(),
                    MARKDOWN
                )
                gasType = GASOLINA_95_E5_PREMIUM
            }
            command("gasolina95e10") {
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = """
                        Quieres echar gasolina 95 E10. Ahora manda tu ubicación y te buscaré las tres más baratas cerca de ti
                        """.trimIndent(),
                    MARKDOWN
                )
                gasType = GASOLINA_95_E10
            }
            command("gasolina98e5") {
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = """
                        Quieres echar gasolina 98 E5. Ahora manda tu ubicación y te buscaré las tres más baratas cerca de ti
                        """.trimIndent(),
                    MARKDOWN
                )
                gasType = GASOLINA_98_E5
            }
            command("gasolina98E10") {
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = """
                        Quieres echar gasolina 98 E10. Ahora manda tu ubicación y te buscaré las tres más baratas cerca de ti
                        """.trimIndent(),
                    MARKDOWN
                )
                gasType = GASOLINA_98_E10
            }
            command("gasoilA") {
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = """
                        Quieres echar gasoil A. Ahora manda tu ubicación y te buscaré las tres más baratas cerca de ti
                        """.trimIndent(),
                    MARKDOWN
                )
                gasType = GASOIL_A
            }
            command("gasoilB") {
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = """
                        Quieres echar gasoil B. Ahora manda tu ubicación y te buscaré las tres más baratas cerca de ti
                        """.trimIndent(),
                    MARKDOWN
                )
                gasType = GASOIL_B
            }
            command("gasoilPremium") {
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = """
                        Quieres echar gasoil Premium. Ahora manda tu ubicación y te buscaré las tres más baratas cerca de ti
                        """.trimIndent(),
                    MARKDOWN
                )
                gasType = GASOIL_PREMIUM
            }
        }
    }.startPolling()

    private fun getNearGasStations(latitude: Float, longitude: Float, gasType: GasType): List<GasStation> {
        val gasStationPersister = GastStationPersisterMongo(getenv("DATABASE_URL"))
        return NearGasStation(gasStationPersister).execute(
            Coordinates(latitude.toDouble(), longitude.toDouble()),
            gasType
        )
    }

    private val commands = listOf(
        "/gasolina95e5",
        "/gasolina95E5Premium",
        "/gasolina95E10",
        "/gasolina98E5",
        "/gasolina98E10",
        "/gasoilA",
        "/gasoilB",
        "/gasoilPremium"
    )
}
