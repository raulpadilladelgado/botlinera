package botlinera.infrastructure.utils

import java.net.URL
import kotlin.text.Charsets.UTF_8

class URLWrapper {
    fun get(url: String): String {
        val connection = URL(url).openConnection()
        connection.setRequestProperty("Accept", "application/json")
        return connection.getInputStream().use {
            it.readBytes()
        }.toString(UTF_8)
    }
}
