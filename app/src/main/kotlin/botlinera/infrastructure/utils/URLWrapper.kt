package botlinera.infrastructure.utils

import java.net.URL

class URLWrapper {
    fun get(url: String): String{
        return URL(url).readText()
    }
}
