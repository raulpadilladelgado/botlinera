package botlinera.infrastructure.bot

import botlinera.application.usecases.NearGasStation
import botlinera.domain.valueobject.Coordinates
import botlinera.domain.valueobject.GasStation
import botlinera.domain.valueobject.GasType
import botlinera.domain.valueobject.GasType.GASOLINA_95_E10
import botlinera.domain.valueobject.GasType.GASOLINA_95_E5
import botlinera.infrastructure.adapters.GastStationPersisterMongo
import botlinera.infrastructure.adapters.UserConfigurationRepositoryInMemory
import botlinera.infrastructure.utils.BotMessages.Companion.findCheapestGasStationsButtonText
import botlinera.infrastructure.utils.BotMessages.Companion.notGasStationsFound
import botlinera.infrastructure.utils.BotMessages.Companion.showingGasStations
import botlinera.infrastructure.utils.BotMessages.Companion.welcoming
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.callbackQuery
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.location
import com.github.kotlintelegrambot.entities.ChatId.Companion.fromId
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.ParseMode.MARKDOWN
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import com.github.kotlintelegrambot.entities.keyboard.KeyboardButton
import java.lang.System.getenv

private const val START_COMMAND = "start"

private const val HELP_COMMAND = "ayuda"

private const val DEFAULT_DISTANCE_IN_METERS = 5000

private val DEFAULT_GAS_TYPE = GASOLINA_95_E5

class TelegramBot {
    fun startPolling() {
        initBot()
    }

    private fun initBot() = bot {
        token = getenv("TELEGRAM_BOT_TOKEN")
        dispatch {
             callbackQuery {
                 val message = callbackQuery.message
                 val location = message!!.replyToMessage!!.location
                 val latitude = location!!.latitude
                 val longitude = location.longitude
                 val gasType = GasType.valueOf(callbackQuery.data)
                 val nearGasStations = getNearGasStations(
                    latitude,
                    longitude,
                    gasType = gasType
                )
                if (nearGasStations.isEmpty()) {
                    bot.sendMessage(
                        fromId(message.chat.id),
                        text = notGasStationsFound(gasType)
                    )
                } else {
                    bot.sendMessage(
                        fromId(message.chat.id),
                        text = showingGasStations(gasType)
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

            location {
                bot.sendMessage(
                        fromId(message.chat.id),
                        "Elige tu gasolina",
                        replyToMessageId = message.messageId,
                        replyMarkup = InlineKeyboardMarkup.create(
                                listOf(
                                        InlineKeyboardButton.CallbackData("95 E5", GASOLINA_95_E5.name),
                                        InlineKeyboardButton.CallbackData("95 E10", GASOLINA_95_E10.name)
                                )
                        )
                )
            }
            command(START_COMMAND) {
                val userConfiguration = UserConfigurationRepositoryInMemory().retrieve(message.chat.id)
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = welcoming(
                        userConfiguration.gasTypePrintable(),
                        userConfiguration.maximumDistanceInKilometers()
                    ),
                    MARKDOWN
                )
            }
            command(HELP_COMMAND) {
                val userConfiguration = UserConfigurationRepositoryInMemory().retrieve(message.chat.id)
                bot.sendMessage(
                    fromId(message.chat.id),
                    text = welcoming(
                        userConfiguration.gasTypePrintable(),
                        userConfiguration.maximumDistanceInKilometers()
                    ),
                    MARKDOWN
                )
            }
            command("buscar") {

            }
        }
    }.startPolling()

    private fun keyboardButtons(
    ): List<List<KeyboardButton>> {
        return listOf(
            listOf(KeyboardButton(findCheapestGasStationsButtonText(), requestLocation = true))
        )
    }

    private fun getNearGasStations(
            latitude: Float,
            longitude: Float,
            maximumDistanceInMeters: Int = DEFAULT_DISTANCE_IN_METERS,
            gasType: GasType = DEFAULT_GAS_TYPE
    ): List<GasStation> {
        val gasStationPersister = GastStationPersisterMongo(getenv("DATABASE_URL"))
        return NearGasStation(gasStationPersister).execute(
            Coordinates(latitude.toDouble(), longitude.toDouble()),
            maximumDistanceInMeters,
            gasType
        )
    }
}
