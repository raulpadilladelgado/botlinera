package botlinera.infrastructure.adapters

import botlinera.application.exceptions.FailedToReplaceGasStations
import botlinera.application.ports.GastStationPersister
import botlinera.domain.valueobject.GasStation
import botlinera.domain.valueobject.GasType
import botlinera.domain.valueobject.GasType.*
import botlinera.domain.valueobject.MaximumCoordinates
import botlinera.infrastructure.dtos.GasStationDto
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.bson.Document
import org.litote.kmongo.KMongo
import org.litote.kmongo.deleteMany
import org.litote.kmongo.getCollectionOfName
import java.util.*
import kotlin.Double.Companion.NaN


private const val ASCENDANT_ORDER = 1

private const val MAX_GAS_STATIONS_TO_RETRIEVE = 3

class GastStationPersisterMongo(url: String) : GastStationPersister {
    private val client: MongoClient = KMongo.createClient(url)
    private val database: MongoDatabase = client.getDatabase("botlinera")
    private val collection = database.getCollectionOfName<GasStationDto>("gas_stations")

    override fun replace(gasStationDto: List<GasStationDto>) = runCatching {
        removeGasStations()
        saveGasStations(gasStationDto)
    }.onFailure {
        throw FailedToReplaceGasStations(it)
    }

    private fun removeGasStations() {
        collection.deleteMany("{}")
    }

    private fun saveGasStations(gasStationDto: List<GasStationDto>) {
        collection.insertMany(gasStationDto)
    }

    override fun queryNearGasStations(coordinates: MaximumCoordinates, gasType: GasType): List<GasStation> {
        val gasPriceToFilter: String = findGasPriceFilterToApplyBy(gasType)
        val query = Document(
            "\$and", Arrays.asList(
                Document("latitude", Document("\$gt", coordinates.maximumSouthCoordinate)),
                Document("latitude", Document("\$lt", coordinates.maximumNorthCoordinate)),
                Document("longitude", Document("\$gt", coordinates.maximumWestCoordinate)),
                Document("longitude", Document("\$lt", coordinates.maximumEastCoordinate)),
                Document(gasPriceToFilter, Document("\$ne", NaN)),
            )
        )

        val gasPriceAscFilter = Document(gasPriceToFilter, ASCENDANT_ORDER)
        val results = mutableListOf<GasStationDto>()
        collection.find(query).sort(gasPriceAscFilter).limit(MAX_GAS_STATIONS_TO_RETRIEVE).into(results)
        return results.map { gasStationDto -> gasStationDto.toDomain() }
    }

    private fun findGasPriceFilterToApplyBy(gasType: GasType): String {
        return when (gasType) {
            GASOLINA_95_E5 -> "gas95E5Price"
            GASOLINA_95_E10 -> "gas95E10Price"
            GASOLINA_95_E5_PREMIUM -> "gas95E5PremiumPrice"
            GASOLINA_98_E5 -> "gas98E5Price"
            GASOLINA_98_E10 -> "gas98E10Price"
            GASOIL_A -> "gasoilA"
            GASOIL_B -> "gasoilB"
            GASOIL_PREMIUM -> "gasoilPremium"
        }
    }
}
