package botlinera.domain.valueobject

class Prices (
    val gas95: Gas95,
    val gas98: Gas98,
    val gasoil: Gasoil
) {

    fun formatted(): String {
        var text = ""

        if (gas95.isNotEmpty() || gas98.isNotEmpty()) {
            text += ("\n💶 Precio Gasolina") + gas95.formatted() + gas98.formatted()
        }

        if (gasoil.isNotEmpty()) {
            text += "\n💶 Precio Gasoil" + gasoil.formatted()
        }

        return text
    }
}
