package botlinera.infrastructure.utils

import botlinera.domain.valueobject.GasType

class BotMessages {
    companion object {
        fun welcoming(gasType: GasType): String {
            return """
                        ¡Hola! Me llamo *Botlinera* 🤖⛽️ y estoy aquí para ayudarte a ahorrar dinero 💸.
                        
                        Para empezar solo tienes que enviarme tu ubicación para así mostrarte las *tres gasolineras más baratas* cerca de ti. 
                        
                        Actualmente se te muestran los resultados priorizando el combustible ${gasType.printableName}. Recuerda que puedes cambiarlo en cualquier momento usando el menú que se encuentra a la izquierda del cuadro para introducir texto
                   """.trimIndent()
        }
        fun unknowMessage(gasType: GasType): String {
            return """
                        Lo siento, no entiendo lo que has escrito. Recuerda que puedes mandarme tu ubicación y te mostraré las gasolineras cercanas más baratas en función del combustible que has seleccionado (${gasType.printableName}).
                        
                        Si necesitas ayuda usa el comando /help. Puedes pulsar sobre el comando anterior o elegirlo en el menú que hay a la izquierda del cuadro para introducir texto.
                   """.trimIndent()
        }
        fun notGasStationsFound(gasType: GasType): String {
            return """
                            Lo siento, no he podido encontrar gasolineras que vendan ${gasType.printableName}
                            
                            Intentalo en otro momento o contacta con los que me programaron
                   """.trimIndent()
        }
        fun chosenGasType(gasType: GasType): String {
            return """
                        Quieres echar ${gasType.printableName}. Ahora manda tu ubicación y te buscaré las tres más baratas cerca de ti
                   """.trimIndent()
        }

        fun showingGasStations(gasType: GasType): String {
            return """
                        Mostrando las gasolineras más baratas cercanas a tu ubicación para el combustible elegido (${gasType.printableName})
                   """.trimIndent()
        }
    }
}
