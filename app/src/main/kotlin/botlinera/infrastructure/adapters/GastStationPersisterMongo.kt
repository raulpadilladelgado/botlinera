package botlinera.infrastucture.adapters

import botlinera.application.ports.GastStationPersister
import botlinera.infrastructure.dtos.GasStation
import org.litote.kmongo.*


class GastStationPersisterMongo : GastStationPersister {
    override fun save(gasStationsInfo: ArrayList<GasStation>) {
        val client = KMongo
            .createClient("mongodb://user:password@localhost:27017/botlinera?authSource=admin&ssl=false")
        val database = client.getDatabase("botlinera")
        val col = database.getCollection<GasStation>()
        col.insertMany(gasStationsInfo)
//        val gasStation = col.findOne(GasStation:: eq "raul_gasofa")
    }
}
