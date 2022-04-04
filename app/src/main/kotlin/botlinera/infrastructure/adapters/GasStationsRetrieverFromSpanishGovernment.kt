package botlinera.infrastructure.adapters

import botlinera.application.ports.GasStationsRetriever
import botlinera.infrastructure.utils.URLWrapper

class GasStationsRetrieverFromSpanishGovernment (private val url: URLWrapper) : GasStationsRetriever{
    override fun apply(): String {
        return url.get("some official source")
    }
}
