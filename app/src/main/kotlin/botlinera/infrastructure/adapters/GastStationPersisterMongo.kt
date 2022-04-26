package botlinera.infrastucture.adapters

import botlinera.application.ports.GastStationPersister
import botlinera.infrastructure.dtos.GasStation
import org.litote.kmongo.*


class GastStationPersisterMongo : GastStationPersister {
    override fun save(gasStationsInfo: List<GasStation>) {
        val client = KMongo
            .createClient(System.getenv("DATABASE_URL"))
        val database = client.getDatabase("botlinera")
        val col = database.getCollection<GasStation>()
        col.insertMany(gasStationsInfo)
    }
}
