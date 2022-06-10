package botlinera.infrastructure.utils

import botlinera.domain.valueobject.GasType

class BotMessages {
    companion object {
        fun welcoming(): String {
            return """
                    ¡Hola! Me llamo *Botlinera* 🤖⛽️ y estoy aquí para ayudarte a ahorrar dinero 💸.
                    
                    Para empezar solo tienes que enviarme tu ubicación para así mostrarte las *tres gasolineras más baratas* cerca de ti. 
                   """.trimIndent()
        }

        fun unknownMessage(): String {
            return """
                        Lo siento, no entiendo lo que has escrito. Recuerda que puedes mandarme tu ubicación y te mostraré las gasolineras cercanas más baratas.
                                                
                        Si necesitas ayuda usa el comando /ayuda. Puedes pulsar sobre el comando anterior o elegirlo en el menú que hay a la izquierda del cuadro para introducir texto.
                   """.trimIndent()
        }

        fun notGasStationsFound(gasType: GasType): String {
            return """
                        Lo siento, no hay gasolineras que vendan ${gasType.printableName} en un radio de 5 kilómetros 😢
                           
                   """.trimIndent()
        }

        fun contactMessage(): String {
            return """
                        Puedes contactar con los que me programaron, intentarán responderte lo antes posible 😀
                        💬 @RaulPadillaDelgado (https://raulpadilladelgado.github.io/)
                        💬 iaimans (iaimans@protonmail.com)
            """.trimIndent()
        }

        fun showingGasStations(gasType: GasType): String {
            return """
                        Mostrando las gasolineras más baratas en un radio aproximado de 5 kilómetros para el combustible elegido (${gasType.printableName})
                   """.trimIndent()
        }

        fun findCheapestGasStationsButtonText(): String {
            return "📍 Compartir ubicación"
        }

        fun chooseGas(): String {
            return "⛽ ️Elige el combustible deseado:"
        }
    }
}
