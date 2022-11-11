package botlinera.infrastructure.bot

import botlinera.application.usecases.RetrieveNearGasStation
import botlinera.domain.valueobject.Coordinates
import botlinera.domain.valueobject.GasStation
import botlinera.domain.valueobject.GasType
import botlinera.domain.valueobject.GasType.*
import botlinera.infrastructure.adapters.GasStationPersisterMongo
import botlinera.infrastructure.dtos.`out`.GasStationDto
import botlinera.infrastructure.utils.BotMessages.Companion.chooseGas
import botlinera.infrastructure.utils.BotMessages.Companion.contactMessage
import botlinera.infrastructure.utils.BotMessages.Companion.findCheapestGasStationsButtonText
import botlinera.infrastructure.utils.BotMessages.Companion.notGasStationsFound
import botlinera.infrastructure.utils.BotMessages.Companion.showingGasStations
import botlinera.infrastructure.utils.BotMessages.Companion.unknownMessage
import botlinera.infrastructure.utils.BotMessages.Companion.welcoming
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.callbackQuery
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.handlers.CallbackQueryHandlerEnvironment
import com.github.kotlintelegrambot.dispatcher.handlers.CommandHandlerEnvironment
import com.github.kotlintelegrambot.dispatcher.handlers.LocationHandlerEnvironment
import com.github.kotlintelegrambot.dispatcher.handlers.TextHandlerEnvironment
import com.github.kotlintelegrambot.dispatcher.location
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId.Companion.fromId
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.KeyboardReplyMarkup
import com.github.kotlintelegrambot.entities.ParseMode.MARKDOWN
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import com.github.kotlintelegrambot.entities.keyboard.KeyboardButton
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollectionOfName
import java.lang.System.getenv

private const val START_COMMAND = "start"

private const val HELP_COMMAND = "ayuda"

private const val CONTACT_COMMAND = "contactar"

private const val DEFAULT_DISTANCE_IN_METERS = 5000

private val DEFAULT_GAS_TYPE = GASOLINA_95_E5

class TelegramBot {
    fun startPolling() {
        initBot()
    }

    private fun initBot() = bot {
        token = getenv("TELEGRAM_BOT_TOKEN")
        dispatch {
            command(START_COMMAND) {
                sendHelpMessage()
            }
            command(HELP_COMMAND) {
                sendHelpMessage()
            }
            command(CONTACT_COMMAND) {
                sendContactMessage()
            }
            text {
                if (text !in listOf("/$START_COMMAND", "/$HELP_COMMAND", "/$CONTACT_COMMAND")){
                    sendUnknownMessage()
                }
            }
            location {
                handleLocation()
            }
            callbackQuery {
                handleGasStationRequest()
            }
        }
    }.startPolling()

    private fun CommandHandlerEnvironment.sendHelpMessage() {
        bot.sendMessage(
            fromId(message.chat.id),
            text = welcoming(),
            MARKDOWN,
            replyMarkup = KeyboardReplyMarkup(locationButton(), resizeKeyboard = true)
        )
    }

    private fun CommandHandlerEnvironment.sendContactMessage() {
        bot.sendMessage(
            fromId(message.chat.id),
            text = contactMessage(),
            MARKDOWN,
            replyMarkup = KeyboardReplyMarkup(locationButton(), resizeKeyboard = true)
        )
    }

    private fun TextHandlerEnvironment.sendUnknownMessage() {
        bot.sendMessage(
            fromId(message.chat.id),
            text = unknownMessage(),
            MARKDOWN,
            replyMarkup = KeyboardReplyMarkup(locationButton(), resizeKeyboard = true)
        )
    }

    private fun LocationHandlerEnvironment.handleLocation() {
        bot.sendMessage(
            fromId(message.chat.id),
            chooseGas(),
            replyToMessageId = message.messageId,
            replyMarkup = InlineKeyboardMarkup.create(
                listOf(
                    listOf(
                        InlineKeyboardButton.CallbackData(GASOLINA_95_E5.printableName,  GASOLINA_95_E5.name),
                        InlineKeyboardButton.CallbackData(GASOLINA_98_E5.printableName, GASOLINA_98_E5.name),
                    ),
                    listOf(
                        InlineKeyboardButton.CallbackData(GASOLINA_95_E10.printableName, GASOLINA_95_E10.name),
                        InlineKeyboardButton.CallbackData(GASOLINA_98_E10.printableName, GASOLINA_98_E10.name),
                    ),
                    listOf(
                        InlineKeyboardButton.CallbackData(GASOIL_A.printableName, GASOIL_A.name),
                        InlineKeyboardButton.CallbackData(GASOLINA_95_E5_PREMIUM.printableName, GASOLINA_95_E5_PREMIUM.name),
                    ),
                    listOf(
                        InlineKeyboardButton.CallbackData(GASOIL_B.printableName, GASOIL_B.name),
                        InlineKeyboardButton.CallbackData(GASOIL_PREMIUM.printableName, GASOIL_PREMIUM.name)
                    )
                )
            )
        )
    }

    private fun CallbackQueryHandlerEnvironment.handleGasStationRequest() {
        val message = callbackQuery.message
        val location = message!!.replyToMessage!!.location
        val latitude = location!!.latitude
        val longitude = location.longitude
        val gasType = valueOf(callbackQuery.data)
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
            nearGasStations.forEach { gasStation ->
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

    private fun locationButton(
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
        val gasStationPersister = GasStationPersisterMongo(mongoCollection())
        return RetrieveNearGasStation(gasStationPersister).execute(
            Coordinates(latitude.toDouble(), longitude.toDouble()),
            maximumDistanceInMeters,
            gasType
        )
    }

    private fun mongoCollection(): MongoCollection<GasStationDto> {
        val client: MongoClient = KMongo.createClient(getenv("DATABASE_URL"))
        val database: MongoDatabase = client.getDatabase("botlinera")
        return database.getCollectionOfName<GasStationDto>("gas_stations")
    }


}
