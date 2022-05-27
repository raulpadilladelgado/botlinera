package botlinera.infrastructure.bot

import botlinera.application.usecases.NearGasStation
import botlinera.domain.valueobject.Coordinates
import botlinera.domain.valueobject.GasStation
import botlinera.infrastucture.adapters.GastStationPersisterMongo
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.location
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId.Companion.fromId
import com.github.kotlintelegrambot.entities.ParseMode
import java.lang.System.getenv

class TelegramBot {
    fun startPolling() {
        initBot()
    }

    private fun initBot() = bot {
        token = getenv("TELEGRAM_BOT_TOKEN")
        dispatch {
            command("help") {
                val result = bot.sendMessage(
                    fromId(message.chat.id),
                    text = """
                        ¡Hola! Me llamo **Botlinera** 🤖⛽️ y estoy aquí para ayudarte a ahorrar dinero 💸.
                        Para empezar solo tienes que enviarme tu ubicación para así mostrarte las **tres gasolineras más baratas** cerca de ti
                        """.trimIndent(),
                    parseMode = ParseMode.MARKDOWN
                )
            }
            text {
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = "¡Hola! Envíame tu ubicación 📍para poder mostrarte las gasolineras más baratas"
                )
            }
            location {
                getNearGasStations(location.latitude, location.longitude)
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
        }
    }.startPolling()

    private fun getNearGasStations(latitude: Float, longitude: Float): List<GasStation> {
        val gasStationPersister = GastStationPersisterMongo(getenv("DATABASE_URL"))
        return NearGasStation(gasStationPersister).execute(Coordinates(latitude.toDouble(), longitude.toDouble()))
    }
}
