package botlinera.infrastructure.bot

import botlinera.application.usecases.NearGasStation
import botlinera.domain.valueobject.Coordinates
import botlinera.domain.valueobject.GasStation
import botlinera.domain.valueobject.GasType
import botlinera.domain.valueobject.GasType.*
import botlinera.infrastructure.adapters.GastStationPersisterMongo
import botlinera.infrastructure.utils.BotMessages
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.location
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId.Companion.fromId
import com.github.kotlintelegrambot.entities.ParseMode.MARKDOWN
import com.github.kotlintelegrambot.entities.keyboard.KeyboardButton
import java.lang.System.getenv

class TelegramBot {
    fun startPolling() {
        initBot()
    }

    private fun initBot() = bot {
        token = getenv("TELEGRAM_BOT_TOKEN")
        var gasType = GASOLINA_95_E5
        var maximumDistanceInMeters = 5000
        dispatch {
            text {
                if (text !in commands) {
                    bot.sendMessage(
                        fromId(message.chat.id),
                        text = BotMessages.unknowMessage(gasType, maximumDistanceInMeters),
                        MARKDOWN
                    )
                }
            }
            KeyboardButton("Pasame tu location ahí", requestLocation = true)
            location {
                val nearGasStations = getNearGasStations(
                    location.latitude,
                    location.longitude,
                    maximumDistanceInMeters,
                    gasType
                )
                if (nearGasStations.isEmpty()) {
                    bot.sendMessage(
                        fromId(message.chat.id), text = BotMessages.notGasStationsFound(gasType, maximumDistanceInMeters)
                    )
                } else {
                    bot.sendMessage(
                        fromId(message.chat.id), text = BotMessages.showingGasStations(gasType, maximumDistanceInMeters)
                    )
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
            }
            command("start") {
                gasType = GASOLINA_95_E5
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = BotMessages.welcoming(gasType, maximumDistanceInMeters),
                    MARKDOWN
                )
            }
            command("ayuda") {
                gasType = GASOLINA_95_E5
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = BotMessages.welcoming(gasType, maximumDistanceInMeters),
                    MARKDOWN
                )
            }
            command("gasolina_95_e5") {
                gasType = GASOLINA_95_E5
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = BotMessages.chosenGasType(gasType),
                    MARKDOWN
                )
            }
            command("gasolina_95_e5_premium") {
                gasType = GASOLINA_95_E5_PREMIUM
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = BotMessages.chosenGasType(gasType),
                    MARKDOWN
                )
            }
            command("gasolina_95_e10") {
                gasType = GASOLINA_95_E10
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = BotMessages.chosenGasType(gasType),
                    MARKDOWN
                )
            }
            command("gasolina_98_e5") {
                gasType = GASOLINA_98_E5
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = BotMessages.chosenGasType(gasType),
                    MARKDOWN
                )
            }
            command("gasolina_98_e10") {
                gasType = GASOLINA_98_E10
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = BotMessages.chosenGasType(gasType),
                    MARKDOWN
                )
            }
            command("gasoil_a") {
                gasType = GASOIL_A
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = BotMessages.chosenGasType(gasType),
                    MARKDOWN
                )
            }
            command("gasoil_b") {
                gasType = GASOIL_B
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = BotMessages.chosenGasType(gasType),
                    MARKDOWN
                )
            }
            command("gasoil_premium") {
                gasType = GASOIL_PREMIUM
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = BotMessages.chosenGasType(gasType),
                    MARKDOWN
                )
            }
            command("distancia_5") {
                maximumDistanceInMeters = 5000
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = BotMessages.chosenDistance(maximumDistanceInMeters),
                    MARKDOWN
                )
            }
            command("distancia_10") {
                maximumDistanceInMeters = 10000
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = BotMessages.chosenDistance(maximumDistanceInMeters),
                    MARKDOWN
                )
            }
            command("distancia_20") {
                maximumDistanceInMeters = 20000
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = BotMessages.chosenDistance(maximumDistanceInMeters),
                    MARKDOWN
                )
            }
        }
    }.startPolling()

    private fun getNearGasStations(latitude: Float, longitude: Float, maximumDistanceInMeters: Int, gasType: GasType): List<GasStation> {
        val gasStationPersister = GastStationPersisterMongo(getenv("DATABASE_URL"))
        return NearGasStation(gasStationPersister).execute(
            Coordinates(latitude.toDouble(), longitude.toDouble()),
            maximumDistanceInMeters,
            gasType
        )
    }

    private val commands = listOf(
        "/start",
        "/ayuda",
        "/gasolina_95_e5",
        "/gasolina_95_e5_premium",
        "/gasolina_95_e10",
        "/gasolina_98_e5",
        "/gasolina_98_e10",
        "/gasoil_a",
        "/gasoil_b",
        "/gasoil_premium",
        "/distancia_5",
        "/distancia_10",
        "/distancia_20"
    )
}
