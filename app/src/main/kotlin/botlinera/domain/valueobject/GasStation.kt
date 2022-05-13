package botlinera.domain.valueobject

class GasStation(
    val name: String,
    val location: Location,
    val prices: Prices
) {
    fun latitude(): Double {
        return location.coordinates.latitude
    }

    fun longitude(): Double {
        return location.coordinates.longitude
    }

    fun formatted(): String {
        val message = """
            ⛽️ $name
            🕐 ${location.time}
            💶 Precio Gasolina
             - 95 E5: ${prices.gas95.e5}€
             - 95 E10: ${prices.gas95.e10}€
             - 95 E5 Premium: ${prices.gas95.e5Premium}€
             - 98 E5: ${prices.gas98.e5}€
             - 98 E10: ${prices.gas98.e10}€
            💶 Precio Gasoil
             - A: ${prices.gasoil.a}€
             - B: ${prices.gasoil.b}€
             - Premium: ${prices.gasoil.premium}€
        """.trimIndent()


        return message
    }

    fun gas95AsText(): String {
        return prices.gas95.e5.toString()
    }
}
