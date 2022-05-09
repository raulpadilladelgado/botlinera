package botlinera.infrastucture.adapters

import botlinera.application.ports.GastStationPersister
import botlinera.domain.valueobject.GasStation
import botlinera.domain.valueobject.MaximumCoordinates
import botlinera.infrastructure.dtos.GasStationDto
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.bson.Document
import org.litote.kmongo.KMongo
import org.litote.kmongo.deleteMany
import org.litote.kmongo.getCollectionOfName
import java.util.*


class GastStationPersisterMongo(url: String) : GastStationPersister {
    private val client: MongoClient = KMongo.createClient(url)
    private val database: MongoDatabase = client.getDatabase("botlinera")
    private val collection = database.getCollectionOfName<GasStationDto>("gas_stations")

    override fun save(gasStationDto: List<GasStationDto>) {
        collection.insertMany(gasStationDto)
    }

    override fun delete() {
        collection.deleteMany("{}")
    }

    override fun queryNearGasStations(coordinates: MaximumCoordinates): List<GasStation> {
        val query = Document(
            "\$and", Arrays.asList(
                Document("latitude", Document("\$gt", coordinates.maximumSouthCoordinate)),
                Document("latitude", Document("\$lt", coordinates.maximumNorthCoordinate)),
                Document("longitude", Document("\$gt", coordinates.maximumWestCoordinate)),
                Document("longitude", Document("\$lt", coordinates.maximumEastCoordinate)),
            )
        )
        val results = mutableListOf<GasStationDto>()
        collection.find(query).into(results)
        return results.map{e -> e.toDomain()}
    }
}
