package botlinera.infrastucture.adapters

import botlinera.application.ports.GastStationPersister
import org.litote.kmongo.*

data class GasStation(val name: String, val age: Int)

class GastStationPersisterMongo : GastStationPersister {
    override fun save(gasStationsInfo: String) {
        val client = KMongo
            .createClient("mongodb://user:password@localhost:27017/botlinera?authSource=admin&ssl=false")
        val database = client.getDatabase("botlinera")
        val col = database.getCollection<GasStation>()
        col.insertOne(GasStation("raul_gasofa", 21))
        val gasStation = col.findOne(GasStation::name eq "raul_gasofa")
        println(gasStation)
    }
}
