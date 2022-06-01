package botlinera.infrastructure.bot

import botlinera.application.usecases.NearGasStation
import botlinera.domain.valueobject.Coordinates
import botlinera.domain.valueobject.GasStation
import botlinera.domain.valueobject.GasType
import botlinera.domain.valueobject.GasType.*
import botlinera.infrastructure.adapters.GastStationPersisterMongo
import botlinera.infrastructure.utils.BotMessages.Companion.changeGasTypeButtonText
import botlinera.infrastructure.utils.BotMessages.Companion.changeMaximumDistanceButtonText
import botlinera.infrastructure.utils.BotMessages.Companion.chosenDistance
import botlinera.infrastructure.utils.BotMessages.Companion.chosenGasType
import botlinera.infrastructure.utils.BotMessages.Companion.findCheapestGasStationsButtonText
import botlinera.infrastructure.utils.BotMessages.Companion.notGasStationsFound
import botlinera.infrastructure.utils.BotMessages.Companion.showingGasStations
import botlinera.infrastructure.utils.BotMessages.Companion.unknowMessage
import botlinera.infrastructure.utils.BotMessages.Companion.welcoming
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.location
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId.Companion.fromId
import com.github.kotlintelegrambot.entities.KeyboardReplyMarkup
import com.github.kotlintelegrambot.entities.ParseMode.MARKDOWN
import com.github.kotlintelegrambot.entities.keyboard.KeyboardButton
import java.lang.System.getenv

private const val START_COMMAND = "start"

private const val HELP_COMMAND = "ayuda"

class TelegramBot {
    fun startPolling() {
        initBot()
    }

    private fun initBot() = bot {
        token = getenv("TELEGRAM_BOT_TOKEN")
        var gasType = GASOLINA_95_E5
        var gasTypePrintable = gasType.printableName
        var maximumDistanceInMeters = 5000
        val maximumDistanceInKilometers = maximumDistanceInMeters / 1000
        dispatch {
            text {
                if (text !in commands) {
                    bot.sendMessage(
                        fromId(message.chat.id),
                        text = unknowMessage(gasTypePrintable, maximumDistanceInKilometers),
                        MARKDOWN
                    )
                }
            }

            location {
                val nearGasStations = getNearGasStations(
                    location.latitude,
                    location.longitude,
                    maximumDistanceInMeters,
                    gasType
                )
                if (nearGasStations.isEmpty()) {
                    bot.sendMessage(
                        fromId(message.chat.id), text = notGasStationsFound(gasTypePrintable, maximumDistanceInKilometers)
                    )
                } else {
                    bot.sendMessage(
                        fromId(message.chat.id), text = showingGasStations(gasTypePrintable, maximumDistanceInKilometers)
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
            command(START_COMMAND) {
                val keyboardButtons = keyboardButtons(gasTypePrintable, maximumDistanceInKilometers)
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = welcoming(gasTypePrintable, maximumDistanceInKilometers),
                    MARKDOWN,
                    replyMarkup = KeyboardReplyMarkup(keyboardButtons, resizeKeyboard = true)
                )
            }
            command(HELP_COMMAND) {
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = welcoming(gasTypePrintable, maximumDistanceInKilometers),
                    MARKDOWN
                )
            }
            command("gasolina_95_e5") {
                gasType = GASOLINA_95_E5
                gasTypePrintable = gasType.printableName
                val keyboardButtons = keyboardButtons(gasTypePrintable, maximumDistanceInKilometers)
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = chosenGasType(gasTypePrintable),
                    MARKDOWN,
                    replyMarkup = KeyboardReplyMarkup(keyboardButtons, resizeKeyboard = true)
                )
            }
            command("gasolina_95_e5_premium") {
                gasType = GASOLINA_95_E5_PREMIUM
                gasTypePrintable = gasType.printableName
                val keyboardButtons = keyboardButtons(gasTypePrintable, maximumDistanceInKilometers)
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = chosenGasType(gasTypePrintable),
                    MARKDOWN,
                    replyMarkup = KeyboardReplyMarkup(keyboardButtons, resizeKeyboard = true)
                )
            }
            command("gasolina_95_e10") {
                gasType = GASOLINA_95_E10
                gasTypePrintable = gasType.printableName
                val keyboardButtons = keyboardButtons(gasTypePrintable, maximumDistanceInKilometers)
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = chosenGasType(gasTypePrintable),
                    MARKDOWN,
                    replyMarkup = KeyboardReplyMarkup(keyboardButtons, resizeKeyboard = true)
                )
            }
            command("gasolina_98_e5") {
                gasType = GASOLINA_98_E5
                gasTypePrintable = gasType.printableName
                val keyboardButtons = keyboardButtons(gasTypePrintable, maximumDistanceInKilometers)
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = chosenGasType(gasTypePrintable),
                    MARKDOWN,
                    replyMarkup = KeyboardReplyMarkup(keyboardButtons, resizeKeyboard = true)
                )
            }
            command("gasolina_98_e10") {
                gasType = GASOLINA_98_E10
                gasTypePrintable = gasType.printableName
                val keyboardButtons = keyboardButtons(gasTypePrintable, maximumDistanceInKilometers)
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = chosenGasType(gasTypePrintable),
                    MARKDOWN,
                    replyMarkup = KeyboardReplyMarkup(keyboardButtons, resizeKeyboard = true)
                )
            }
            command("gasoil_a") {
                gasType = GASOIL_A
                gasTypePrintable = gasType.printableName
                val keyboardButtons = keyboardButtons(gasTypePrintable, maximumDistanceInKilometers)
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = chosenGasType(gasTypePrintable),
                    MARKDOWN,
                    replyMarkup = KeyboardReplyMarkup(keyboardButtons, resizeKeyboard = true)
                )
            }
            command("gasoil_b") {
                gasType = GASOIL_B
                gasTypePrintable = gasType.printableName
                val keyboardButtons = keyboardButtons(gasTypePrintable, maximumDistanceInKilometers)
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = chosenGasType(gasTypePrintable),
                    MARKDOWN,
                    replyMarkup = KeyboardReplyMarkup(keyboardButtons, resizeKeyboard = true)
                )
            }
            command("gasoil_premium") {
                gasType = GASOIL_PREMIUM
                gasTypePrintable = gasType.printableName
                val keyboardButtons = keyboardButtons(gasTypePrintable, maximumDistanceInKilometers)
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = chosenGasType(gasTypePrintable),
                    MARKDOWN,
                    replyMarkup = KeyboardReplyMarkup(keyboardButtons, resizeKeyboard = true)
                )
            }
            command("distancia_5") {
                maximumDistanceInMeters = 5000
                val keyboardButtons = keyboardButtons(gasTypePrintable, maximumDistanceInKilometers)
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = chosenDistance(maximumDistanceInKilometers),
                    MARKDOWN,
                    replyMarkup = KeyboardReplyMarkup(keyboardButtons, resizeKeyboard = true)
                )
            }
            command("distancia_10") {
                maximumDistanceInMeters = 10000
                val keyboardButtons = keyboardButtons(gasTypePrintable, maximumDistanceInKilometers)
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = chosenDistance(maximumDistanceInKilometers),
                    MARKDOWN,
                    replyMarkup = KeyboardReplyMarkup(keyboardButtons, resizeKeyboard = true)
                )
            }
            command("distancia_20") {
                maximumDistanceInMeters = 20000
                val keyboardButtons = keyboardButtons(gasTypePrintable, maximumDistanceInKilometers)
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = chosenDistance(maximumDistanceInKilometers),
                    MARKDOWN,
                    replyMarkup = KeyboardReplyMarkup(keyboardButtons, resizeKeyboard = true)
                )
            }
        }
    }.startPolling()

    private fun keyboardButtons(gasTypePrintable: String, maximumDistanceInKilometers: Int): List<List<KeyboardButton>> {
        return listOf(
            listOf(KeyboardButton(findCheapestGasStationsButtonText(), requestLocation = true)),
            listOf(
                KeyboardButton(changeGasTypeButtonText(gasTypePrintable)),
                KeyboardButton(changeMaximumDistanceButtonText(maximumDistanceInKilometers))
            )
        )
    }

    private fun getNearGasStations(latitude: Float, longitude: Float, maximumDistanceInMeters: Int, gasType: GasType): List<GasStation> {
        val gasStationPersister = GastStationPersisterMongo(getenv("DATABASE_URL"))
        return NearGasStation(gasStationPersister).execute(
            Coordinates(latitude.toDouble(), longitude.toDouble()),
            maximumDistanceInMeters,
            gasType
        )
    }

    private val commands = listOf(
        "/$START_COMMAND",
        "/$HELP_COMMAND",
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
