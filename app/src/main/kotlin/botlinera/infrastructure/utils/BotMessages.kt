package botlinera.infrastructure.utils

import botlinera.domain.valueobject.GasType

class BotMessages {
    companion object {
        fun welcoming(gasType: GasType, maximumDistanceInMeters: Int): String {
            return """
                        ¡Hola! Me llamo *Botlinera* 🤖⛽️ y estoy aquí para ayudarte a ahorrar dinero 💸.
                        
                        Para empezar solo tienes que enviarme tu ubicación para así mostrarte las *tres gasolineras más baratas* cerca de ti. 
                        
                        Actualmente se te muestran los resultados priorizando el combustible ${gasType.printableName} y con una distancia máxima aproximada de ${maximumDistanceInMeters / 1000} kilómetros. Recuerda que puedes cambiar estos ajustes en cualquier momento usando el menú que se encuentra a la izquierda del cuadro para introducir texto o pulsando sobre los links que te dejo aquí abajo:
                        /ayuda -> Muestra la ayuda
                        /gasolina_95_e5 -> Gasolina 95 E5
                        /gasolina_95_e5_premium -> Gasolina 95 E5 Premium
                        /gasolina_95_e10 -> Gasolina 95 E10
                        /gasolina_98_e5 -> Gasolina 98 E5
                        /gasolina_98_e10 -> Gasolina 98 E10
                        /gasoil_a -> Gasoil A
                        /gasoil_b -> Gasoil B
                        /gasoil_premium -> Gasoil Premium
                   """.trimIndent()
        }
        fun unknowMessage(gasType: GasType, maximumDistanceInMeters: Int): String {
            return """
                        Lo siento, no entiendo lo que has escrito. Recuerda que puedes mandarme tu ubicación y te mostraré las gasolineras cercanas más baratas en función del combustible que has seleccionado (${gasType.printableName}) y la distancia máxima aproximada de ${maximumDistanceInMeters / 1000} kilómetros
                        
                        Si necesitas ayuda usa el comando /help. Puedes pulsar sobre el comando anterior o elegirlo en el menú que hay a la izquierda del cuadro para introducir texto.
                   """.trimIndent()
        }
        fun notGasStationsFound(gasType: GasType, maximumDistanceInMeters: Int): String {
            return """
                            Lo siento, no he podido encontrar gasolineras que vendan ${gasType.printableName} en una distancia máxima aproximada de ${maximumDistanceInMeters / 1000} kilómetros
                            
                            Intentalo en otro momento o contacta con los que me programaron 
                            @RaulPadillaDelgado (https://raulpadilladelgado.github.io/)
                            Isaac Aiman ()
                   """.trimIndent()
        }
        fun chosenGasType(gasType: GasType): String {
            return """
                        Quieres echar ${gasType.printableName}. Ahora manda tu ubicación y te buscaré las tres más baratas cerca de ti
                   """.trimIndent()
        }

        fun showingGasStations(gasType: GasType, maximumDistanceInMeters: Int): String {
            return """
                        Mostrando las gasolineras más baratas a una distancia máxima de ${maximumDistanceInMeters / 1000} kilómetros para el combustible elegido (${gasType.printableName})
                   """.trimIndent()
        }

        fun chosenDistance(maximumDistanceInMeters: Int): String {
            return """
                        Se ha establecido ${maximumDistanceInMeters / 1000} kilómetros como la distancia máxima aproximada para la búsqueda de gasolineras
                   """.trimIndent()
        }
    }
}
