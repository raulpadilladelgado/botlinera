package botlinera.infrastructure.utils

import java.net.URL

class URLWrapper {
    fun get(url: String): String {
        val connection =  URL(url).openConnection()
        connection.setRequestProperty("Accept", "application/json")
        val response = connection.getInputStream().use {
            it.readBytes()
        }.toString(Charsets.UTF_8)
        return response
    }
}
