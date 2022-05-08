package botlinera.infrastucture.adapters

import botlinera.application.ports.GastStationPersister
import botlinera.domain.valueobject.Coordinates
import botlinera.domain.valueobject.GasStation
import botlinera.infrastructure.dtos.GasStationDto
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo
import org.litote.kmongo.deleteMany
import org.litote.kmongo.getCollectionOfName


class GastStationPersisterMongo(url: String) : GastStationPersister {
    private val client: MongoClient = KMongo.createClient(url)
    private val database: MongoDatabase = client.getDatabase("botlinera")
    private val collection = database.getCollectionOfName<GasStationDto>("gas_stations")

    override fun save(gasStationsInfo: List<GasStationDto>) {
        collection.insertMany(gasStationsInfo)
    }

    override fun delete() {
        collection.deleteMany("{}")
    }

    override fun queryNearGasStations(coordinates: Coordinates): List<GasStation> {
        TODO("Not yet implemented")
    }
}
