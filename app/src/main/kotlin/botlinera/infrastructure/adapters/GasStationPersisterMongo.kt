package botlinera.infrastructure.adapters

import botlinera.application.exceptions.FailedToQueryNearGasStations
import botlinera.application.exceptions.FailedToReplaceGasStations
import botlinera.application.ports.GasStationPersister
import botlinera.domain.valueobject.GasStation
import botlinera.domain.valueobject.GasType
import botlinera.domain.valueobject.GasType.*
import botlinera.domain.valueobject.MaximumCoordinates
import botlinera.infrastructure.dtos.GasStationDto
import com.mongodb.client.MongoCollection
import org.bson.Document
import org.litote.kmongo.deleteMany
import java.lang.Double.NaN
import java.util.*

private const val ASCENDANT_ORDER = 1

private const val MAX_GAS_STATIONS_TO_RETRIEVE = 3

class GasStationPersisterMongo(private val collection: MongoCollection<GasStationDto>) : GasStationPersister {

    override fun replace(gasStationDto: List<GasStation>) = runCatching {
        removeGasStations()
        saveGasStations(gasStationDto)
    }.onFailure {
        throw FailedToReplaceGasStations(it)
    }

    override fun queryNearGasStations(coordinates: MaximumCoordinates, gasType: GasType) =
        runCatching {
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
            collection.find(query)
                .sort(gasPriceAscFilter)
                .limit(MAX_GAS_STATIONS_TO_RETRIEVE)
                .into(results)
            results.map { gasStationDto -> gasStationDto.toDomain() }
        }.onFailure {
            throw FailedToQueryNearGasStations(it)
        }

    private fun removeGasStations() {
        collection.deleteMany("{}")
    }

    private fun saveGasStations(gasStation: List<GasStation>) {
        collection.insertMany(gasStation.map { GasStationDto.from(it) })
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
